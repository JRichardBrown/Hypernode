package decisionTree;

public class IDGenerator {
	//Generate ID when parent is Info-Type node
    public static String genID(String parentID) {
        //Split the parent node ID into two parts: the string and the node count	
    	String[] idParts = parentID.split("_");
    	
    	int nodeCount = Integer.parseInt(idParts[1]);
    	
		nodeCount++;    //increase the node count
		
	    return idParts[0] + "_" + nodeCount;
    }
    
	//Generate ID when parent is Challenge-Type or Contest-Type node
    public static String genID(String parentID, String rslt) {
        //Split the parent node ID into two parts: the string and the node count	
    	String[] idParts = parentID.split("_");
    	
    	int nodeCount = Integer.parseInt(idParts[1]);
		nodeCount = 1;    //reset the node count
		
	    return idParts[0] + rslt + "_" + nodeCount;
    }
    
	//Generate ID when parent is Decision-Type node
    public static String genID(String parentID, int sel) {
        //Split the parent node ID into two parts: the string and the node count	
    	String[] idParts = parentID.split("_");
    	
    	int nodeCount = Integer.parseInt(idParts[1]);
		nodeCount = 1;    //reset the node count
		
		return idParts[0] + sel + "_" + nodeCount;
    }
}
