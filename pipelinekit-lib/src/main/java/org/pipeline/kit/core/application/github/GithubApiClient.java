package org.pipeline.kit.core.application.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.kohsuke.github.GHRepository;
import org.pipeline.kit.core.application.RestClient;
import org.pipeline.kit.core.application.github.repository.details.Root;
import org.pipeline.kit.core.application.github.repository.details.Tree;
import org.pipeline.kit.core.domain.repository.File;
import org.pipeline.kit.core.domain.repository.Folder;
import org.pipeline.kit.core.domain.repository.Repository;
import org.pipeline.kit.extneral.HttpRestClient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Repository getRepoContent(Repository repoDetails) throws IOException, InterruptedException {
        Map<String, Folder> folderMap = new HashMap<>();
        repoDetails.setFolderList(new HashMap<>());
        repoDetails.setFileList(new HashMap<>());
        String repoContent = this.restClient.getRepoContent(token, repoDetails);
        Root root = objectReader.readValue(repoContent, Root.class);

        for (Tree t : root.getTree()) {
            String path = t.getPath();
            String type = t.getType();

            if (!path.contains("/")) {
                if (t.getType().equals("tree")) {
                    HashMap<String, Folder> folderList = repoDetails.getFolderList();
                    folderList.put(path, Folder.builder()
                            .id(t.getSha())
                            .name(path)
                            .build());
                } else {
                    HashMap<String, File> fileList = repoDetails.getFileList();
                    fileList.put(path, new File(t.getSha(), path));
                }
            } else {
                if (t.getType().equals("tree")) {
                    String parentPath = path.substring(0, path.lastIndexOf('/'));
                    Folder startFolder = repoDetails.getFolderList().get(parentPath.split("/")[0]);
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
                    String parentPath = path.substring(0, path.lastIndexOf('/'));
                    Folder startFolder = repoDetails.getFolderList().get(parentPath.split("/")[0]);
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

//        for (Tree t : root.getTree()) {
//            String path = t.getPath();
//            String type = t.getType();
//
//            if (type.equals("blob")) {
//                String name = path.substring(path.lastIndexOf('/') + 1);
//                String id = t.getSha();
//
//                String parentPath = path.substring(0, path.lastIndexOf('/'));
//                Folder parentFolder = folderMap.get(parentPath);
//
//                if (parentFolder == null) {
//                    parentFolder = Folder.builder()
//                            .subFolders(new ArrayList<>())
//                            .files(new ArrayList<>())
//                            .build();
//                    folderMap.put(parentPath, parentFolder);
//                }
//
//                parentFolder.getFiles().add(new File(name, id));
//            } else if (type.equals("tree")) {
//                if (!path.contains("/")) {
//                    continue;
//                }
//
//                String parentPath = path.substring(0, path.lastIndexOf('/'));
//                Folder parentFolder = folderMap.get(parentPath);
//
//                if (parentFolder == null) {
//                    parentFolder =  Folder.builder()
//                            .subFolders(new ArrayList<>())
//                            .files(new ArrayList<>())
//                            .build();
//                    folderMap.put(parentPath, parentFolder);
//                }
//
//                Folder folder = folderMap.get(path);
//
//                if (folder == null) {
//                    folder = Folder.builder()
//                            .subFolders(new ArrayList<>())
//                            .files(new ArrayList<>())
//                            .build();
//                    folderMap.put(path, folder);
//                }
//
//                parentFolder.getSubFolders().add(folder);
//
//                return null;
//            }
//
//
//        }
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
