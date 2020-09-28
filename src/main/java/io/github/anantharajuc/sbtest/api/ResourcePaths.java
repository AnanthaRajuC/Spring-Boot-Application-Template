package io.github.anantharajuc.sbtest.api;

public class ResourcePaths 
{
	public static final String API         = "api";
    
    public static final String V1          = "/v1";
    public static final String V2          = "/v2";
    
    public static final String ID          = "/{id}";
    public static final String GENDER      = "gender/{gender}";
    
    public static final String ROOT_API    = "/" + API;                               //  /api
    public static final String ROOT_API_V1 = ROOT_API + V1;		                      //  /api/v1
    
    public class Person
    {
    	public static final String NAME = "/person";
    	
    	public final class V1
    	{
    		private V1() {}
    		
    		public static final String ROOT           = ROOT_API_V1 + NAME;           //  /api/v1/person
    		
            public static final String GET            = ROOT_API_V1 + NAME + ID;      //  /api/v1/person/{id} 
            
            public static final String GET_BY_GENDER  = GENDER;                       //  /gender/{gender} 
    	}
    }
}
