package com.mock.utils;


import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.util.FS;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import lombok.extern.slf4j.Slf4j;
@Slf4j

public class JgitSSHUtils {
	
	public static String  path= new File(System.getProperty("user.dir")).getParent();   
	
	
	public static String localPath = new File(System.getProperty("user.dir")).getParent() + "/service-config/";
	private static String localGitPath = localPath + ".git";
	
	public static  String remotePath  = "git@g.git";
	public static  String branch = "master";
	private static String keyPath= path + "/id_rsa";	
	public static  Git git =null;	
	
	  //ssh session的工厂,用来创建密匙连接
	private static SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
        @Override
        protected void configure(OpenSshConfig.Host host, Session session ) {
            session.setConfig("StrictHostKeyChecking","no");
        }
        @Override
        protected JSch createDefaultJSch(FS fs) throws JSchException {
            JSch sch = super.createDefaultJSch(fs);
            sch.addIdentity(keyPath); //添加私钥文件
            return sch;
        }
      };
      
      //初始化
      static {
    	  File file = new File(localGitPath); 
	        if (!file.exists()) { 
	          //如果沒有.git文件
	        	gitClone();
	        }else {
	        	//如果有 初始化git
	        	  //关联到本地仓库
	            FileRepository fileRepository=null;
				try {
					fileRepository = new FileRepository(new File(localGitPath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(git==null) {
				   git = new Git(fileRepository);
				}
	        }    
      }
      
      
	//localRepoPath 为本地文件夹
    //keyPath 私钥文件 path
    //remoteRepoPath 为 ssh git远端仓库地址
    protected static void gitClone() {
        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();
        Git git = null;
        try {
            git = cloneCommand.setURI(remotePath) //设置远程URI
                .setTransportConfigCallback(transport -> {
                    SshTransport sshTransport = ( SshTransport )transport;
                    sshTransport.setSshSessionFactory( sshSessionFactory );
                })
                .setDirectory(new File(localPath)) //设置下载存放路径
                .call();
            log.info("git clone success");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("git clone success");
        }finally {
            if (git != null) {
                git.close();
            }
        }
    }
	
 
	/**
	 * pull远程
	 */
	public static void pull() {
		try {
            //设置密钥,拉取文件
            PullCommand pullCommand = git
                    .pull()
                    .setTransportConfigCallback(
                            transport -> {
                                SshTransport sshTransport = ( SshTransport )transport;
                                sshTransport.setSshSessionFactory( sshSessionFactory );
                            });
            pullCommand.call();
        }catch (Exception e) {
            e.printStackTrace();
        }
	    }
	

	/**
	 * 将增加的所有文件加入Git
	 *
	 * @throws Exception
	 */
	public static void add_commit_push_all(String message)  {	
		try {
            //设置密钥,拉取文件
            AddCommand addCommand = git.add();       
            addCommand.addFilepattern(".").call();
            
            CommitCommand commitCommand = git.commit();
            commitCommand.setMessage(message);
            commitCommand.setAllowEmpty(true);
            commitCommand.call();            
            PushCommand pushCommand = git.push() 
            .setTransportConfigCallback(
                  transport -> {
                      SshTransport sshTransport = ( SshTransport )transport;
                      sshTransport.setSshSessionFactory( sshSessionFactory );
                  });
            
            pushCommand.setForce(true).setPushAll();
            Iterable<PushResult> iterable = pushCommand.call();
//            for (PushResult pushResult : iterable) {
//                System.out.println(pushResult.toString());
//            }
        }catch (Exception e) {
            e.printStackTrace();
        }		
	}


	
	
	
	public static void main(String[] args)   {
		add_commit_push_all("String message");
	
		//localGitPath
		//pull();
		//create();	
		//addAll();
		//  path=path.substring(0,path.lastIndexOf("/"));
	
//	       File file = new File(path);
//	        String strParentDirectory = file.getParent();
//	        System.out.println("文件的上级目录为 : " + strParentDirectory);
		System.out.println(keyPath);
//		
//		System.out.println(path);
//	String	path1=path.substring(0,path.lastIndexOf("/"));
//	System.out.println(path1);
	//	gitClone();
//		
//		String path = System.getProperty("user.dir");
//		System.out.println(path);
		//cloneBranch(null);
//		addAll();
//		commit("String message");
//		push();
		//pull(null);
		
	}
}