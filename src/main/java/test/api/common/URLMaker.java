package test.api.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.api.util.FunctionalUtility;

public class URLMaker {

	private String baseURI;
	private String path;
	private List<SignatureEntity> pathParams=new ArrayList<SignatureEntity>();
	private Map<String,String> queryParams=new HashMap<String,String>();
	
	public URLMaker(String baseURI, String path){
		this.baseURI=baseURI;
		this.path=path;
	}
	
	public URLMaker(String baseURI, String path, List<SignatureEntity> pathParams){
		this.baseURI=baseURI;
		this.path=path;
		this.pathParams.addAll(pathParams);
	}

	public URLMaker(String baseURI, String path, Map<String,String> queryParams){
		this.baseURI=baseURI;
		this.path=path;
		this.queryParams.putAll(queryParams);
	}
	
	public URLMaker(String baseURI, String path, List<SignatureEntity> pathParams, Map<String,String> queryParams){
		this.baseURI=baseURI;
		this.path=path;
		this.pathParams.addAll(pathParams);
		this.queryParams.putAll(queryParams);
	}
	
	public String getURL(){
		String url="";
		url=buildURL();
		return url;
	}
	
	private String buildURL(){
		String url="";
		if(pathParams.isEmpty()){
			if(queryParams.isEmpty()){
				url=baseURI+path;
			}
			else{
				String param="?";
				for(String par:queryParams.keySet()){
					param=param+par+"="+queryParams.get(par);
					param=param+"&";
				}
				param=FunctionalUtility.replaceLast(param,"&", "");
				url=baseURI+path+param;
			}
		}
		else{
			if(queryParams.isEmpty()){
				ArrayList<String> spath=FunctionalUtility.dataSplitter(path, "/");
				for(SignatureEntity se:pathParams){
					for(int index=0;index<spath.size();index++){
						if(spath.get(index).equals(se.getKey())){
							spath.set(index,se.getValue());
						}
					}
				}
				String npath="";
				for(String s:spath){
					npath=npath+s+"/";
				}
				npath=FunctionalUtility.replaceLast(npath,"/", "");
				url=baseURI+npath;
			}
			else{
				
				ArrayList<String> spath=FunctionalUtility.dataSplitter(path, "/");
				for(SignatureEntity se:pathParams){
					for(int index=0;index<spath.size();index++){
						if(spath.get(index).equals(se.getKey())){
							spath.set(index,se.getValue());
						}
					}
				}
				String npath="";
				for(String s:spath){
					npath=npath+s+"/";
				}
				npath=FunctionalUtility.replaceLast(npath,"/", "");
				url=baseURI+npath;
				
				String param="?";
				for(String par:queryParams.keySet()){
					param=param+par+"="+queryParams.get(par);
					param=param+"&";
				}
				param=FunctionalUtility.replaceLast(param,"&", "");
				url=url+param;
			}
		}
		
		return url;
	}
}
