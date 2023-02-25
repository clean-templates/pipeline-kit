package org.pipeline.kit.core.application.github.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.kohsuke.github.GHRepository;
import org.pipeline.kit.core.application.RestClient;
import org.pipeline.kit.core.application.github.repository.details.Root;
import org.pipeline.kit.core.application.github.repository.details.Tree;
import org.pipeline.kit.core.domain.provider.PullRequestDetails;
import org.pipeline.kit.core.domain.repository.File;
import org.pipeline.kit.core.domain.repository.Folder;
import org.pipeline.kit.core.domain.repository.Repository;
import org.pipeline.kit.external.HttpRestClient;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class GithubApiClient implements IGithubApiClient {

    private String token;
    private final ObjectReader objectReader = new ObjectMapper().reader();
    private static final String GIT_TREES = "/git/trees";
    private static final String RECURSIVE_PARAM = "?recursive=true";
    private final String GITHUB_BASE_API = "https://api.github.com/repos/";


    private RestClient restClient;

    public GithubApiClient(String token) {
        this.token = token;
        this.restClient = new HttpRestClient();
    }

    @Override
    public URI buildUri(GHRepository repository, String sha) {
        return URI.create(GITHUB_BASE_API
                .concat(repository.getFullName())
                .concat(GIT_TREES)
                .concat(sha)
                .concat(RECURSIVE_PARAM));
    }

    @Override
    public Repository getRepoDetails(String repositoryName, String repositoryBranch) throws IOException, InterruptedException {
        String repoDetails = this.restClient.getRepoDetails(repositoryName, repositoryBranch);
        Root root = objectReader.readValue(repoDetails, Root.class);
        return Repository.builder()
                .url(root.getUrl())
                .name(repositoryName)
                .branch(repositoryBranch)
                .headCommit(root.getObject().getSha())
                .build();
    }

    @Override
    public void createBranch(String repositoryName, String branchName, String headCommit) throws IOException, InterruptedException {
        this.restClient.createBranch(repositoryName, branchName, headCommit, token);
    }

    @Override
    public String getBranchHeadCommit(String repositoryName, String branchName) throws IOException, InterruptedException {
        return getRepoDetails(repositoryName, branchName).getHeadCommit();
    }

    @Override
    public void createPullRequest(PullRequestDetails pullRequestDetails) throws IOException, InterruptedException {
        this.restClient.createPullRequest(pullRequestDetails, token);
    }

    // TODO: abstract the github build
    @Override
    public Repository getRepoContent(Repository repoDetails) throws IOException, InterruptedException {
        Map<String, Folder> folderMap = new HashMap<>();
        repoDetails.setFolderList(new HashMap<>());
        repoDetails.setFileList(new HashMap<>());
        String repoContent = this.restClient.getRepoContent(token, repoDetails);
        Root root = objectReader.readValue(repoContent, Root.class);

        for (Tree t : root.getTree()) {
            String path = t.getPath();

            if (isRootDirectory(path)) {
                populateInitialRootDirectory(repoDetails, t, path);
            } else {
                if (isFolder(t)) {
                    String parentPath = getParentPath(path);
                    Folder startFolder = getStartFolder(repoDetails, parentPath);
                    Folder subfolder = findSubfolder(parentPath, startFolder);
                    if (subfolder.getSubFolders() == null) {
                        subfolder.setSubFolders(new HashMap<>());
                        subfolder.getSubFolders().put(path, Folder.builder()
                                .id(t.getSha())
                                .name(path)
                                .build());
                    } else {
                        subfolder.getSubFolders().put(path, Folder.builder()
                                .id(t.getSha())
                                .name(path)
                                .build());
                    }
                } else {
                    String parentPath = getParentPath(path);
                    Folder startFolder = getStartFolder(repoDetails, parentPath);
                    Folder subfolder = findSubfolder(parentPath, startFolder);
                    if (subfolder.getFiles() == null) {
                        subfolder.setFiles(new HashMap<>());
                        subfolder.getFiles().put(path, new File(t.getSha(), path));
                    } else {
                        subfolder.getFiles().put(path, new File(t.getSha(), path));
                    }
                }
            }
        }
            System.out.println(repoDetails);
            return repoDetails;


        }

    private static Folder getStartFolder(Repository repoDetails, String parentPath) {
        return repoDetails.getFolderList().get(parentPath.split("/")[0]);
    }

    private static String getParentPath(String path) {
        return path.substring(0, path.lastIndexOf('/'));
    }

    private static void populateInitialRootDirectory(Repository repoDetails, Tree t, String path) {
        if (isFolder(t)) {
            HashMap<String, Folder> folderList = repoDetails.getFolderList();
            folderList.put(path, Folder.builder()
                    .id(t.getSha())
                    .name(path)
                    .build());
        } else {
            HashMap<String, File> fileList = repoDetails.getFileList();
            fileList.put(path, new File(t.getSha(), path));
        }
    }

    private static boolean isFolder(Tree t) {
        return t.getType().equals("tree");
    }

    private static boolean isRootDirectory(String path) {
        return !path.contains("/");
    }

    public Folder findSubfolder(String name, Folder folder) {
        if (folder.getName().equals(name)) {
            return folder;
        }

        if (folder.getSubFolders() != null) {
            for (Folder subfolder : folder.getSubFolders().values()) {
                Folder found = findSubfolder(name, subfolder);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
