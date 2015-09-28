package sodexoPoc;


	public enum Role {
	    USER(0), ADMIN(1), DISTRIBUTER(2) ;
	    private final int value;

	    Role(int value) {
	        this.value = value;
	    }
	    
	    public int getValue() {
	        return this.value;
	    }
	    
	    public static Role getRole(int i)
	    {
	    	switch(i)
	    	{
	    		case 0: return USER;
	    		case 1: return ADMIN;
	    		case 2: return DISTRIBUTER;
	    		default: return USER;
	    	}
	    }
	    
	}

