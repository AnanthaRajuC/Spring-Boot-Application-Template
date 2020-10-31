package io.github.anantharajuc.sbat.api;

public class ResourcePaths 
{
	public static final String API         = "api";                       
    
    public static final String V1          = "/v1";                                   
    public static final String V2          = "/v2";									  
    
    public static final String ID          = "/{id}";								 				
    public static final String PAGEABLE    = "/pageable";							  
    public static final String GENDER      = "/gender/{gender}";					  
    
    public static final String ROOT_API    = "/" + API;                                /*   /api                        */
    public static final String ROOT_API_V1 = ROOT_API + V1;   					       /*   /api/v1                     */
    
    public class PersonManagement
    {
    	public static final String NAME = "/management/person";
    	
    	public final class V1
    	{
    		private V1() {}
    		
    		public static final String ROOT           = ROOT_API_V1 + NAME;            /*	/api/v1/management/person   */
    	}
    }
    
    public class Person
    {
    	public static final String NAME = "/person";
    	
    	public final class V1
    	{
    		private V1() {}
    		
    		public static final String ROOT           = ROOT_API_V1 + NAME;            /*	/api/v1/person              */
    		
            public static final String GET            = ROOT_API_V1 + NAME + ID;       /*	/api/v1/person/id           */
            
            public static final String GET_BY_GENDER  = GENDER;                         
            
            public static final String PAGEABLE       = GENDER;                             
    	}
    }
    
    public class SBAT
    {
    	public static final String NAME = "/sbat";
    	
    	public final class V1
    	{
    		private V1() {}
    		
    		public static final String ROOT           = NAME;
    		
    		public static final String PERSONS        = "/persons";
    		public static final String INDEX          = "/index";
    		public static final String LOGIN          = "/login";
    		public static final String ABOUT          = "/about";
    		public static final String SETTINGS       = "/settings";
    		public static final String TECH_STACK     = "/tech-stack";
    		public static final String CLOSE          = "/close";
    	}
    }
}
