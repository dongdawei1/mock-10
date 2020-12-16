package com.mock.utils;

import static com.mock.utils.PropertiesUtil.getProperty;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.RmCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.AbortedByHookException;
import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.RefNotAdvertisedException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import com.mock.constant.Constants;

import lombok.extern.slf4j.Slf4j;
@Slf4j

public class JgitUtil {
	private static String localPath = Constants.TEMPORARY_PATH;
	private static String localGitPath = localPath + "/.git";
	
	
//	private static final String Constants.REMOTEPATH = getProperty("git.remotePath");
//	private static final String Constants.GITUSERNAME = getProperty("git.gitusername");
//	private static final String Constants.GITPASSWORD = getProperty("git.gitpassword");
//	private static final String Constants.GITBRANCH = getProperty("git.gitbranch");
	
	private static Repository localRepository;
	private static Git git;
	
	
	static {
		try {
			localRepository = new FileRepository(localGitPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		git = new Git(localRepository);
	}

	/**
	 * 创建本地仓库
	 *
	 * @throws IOException
	 */
	public static void create()  {
		try {
			Repository repository= new FileRepository(localGitPath);
			repository.create();
			log.info("create success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * clone克隆远程分支到本地
	 *
	 * @param branchName
	 * @throws GitAPIException
	 */
	public static void cloneBranch(String branchName)  {
		if(branchName==null) {
			branchName=Constants.GITBRANCH;
			
		}
		System.out.print(Constants.REMOTEPATH);
		try {
			Git.cloneRepository().setURI(Constants.REMOTEPATH).setBranch(branchName).setDirectory(new File(localPath)).call();
			log.info("clone success");
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * pull远程代码
	 *
	 * @param branchName 远程分支名称
	 * @throws Exception
	 */
	public static void pull(String branchName) {
		if(branchName==null) {
			branchName=Constants.GITBRANCH;
			log.info("pull success");
		}
		try {
			git.pull().setRemoteBranchName(branchName).call();
		} catch (WrongRepositoryStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CanceledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RefNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RefNotAdvertisedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoHeadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 将单个文件加入Git
	 *
	 * @param fileName 添加文件名
	 * @throws Exception
	 */
	public static void add(String fileName) {
		File myFile = new File(localPath + fileName);
		try {
			myFile.createNewFile();
			git.add().addFilepattern(fileName).call();
			log.info("add success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoFilepatternException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 将增加的所有文件加入Git
	 *
	 * @throws Exception
	 */
	public static void addAll()  {
		try {
			git.add().addFilepattern(".").call();
			log.info("add success");
		} catch (NoFilepatternException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 提交文件
	 *
	 * @param message 提交备注
	 * @throws Exception
	 */
	public static void commit(String message)  {
		try {
			git.commit().setMessage(message).call();
			log.info("commit success");
		} catch (NoHeadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnmergedPathsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConcurrentRefUpdateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongRepositoryStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AbortedByHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 同步远程仓库
	 *
	 * @throws Exception
	 */
	public static void push()  {
		try {
			git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(Constants.GITUSERNAME, Constants.GITPASSWORD)).call();
			log.info("push success");
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)  {
//		
//		String path = System.getProperty("user.dir");
//		System.out.println(path);
		cloneBranch(null);
//		addAll();
//		commit("String message");
//		push();
		//pull(null);
		
	}
}