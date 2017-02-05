/* SIDHU RAJVEER 	cs610 PrP 5324 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class hdec5324 {
	
	protected HeapPriorityQueue5324<Integer,LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>> minHeap = new HeapPriorityQueue5324<Integer,LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>>();
	protected Integer[] freqTable=new Integer[256];
	protected LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>> huffmanTree=new LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>();
    protected static String decodefileName;
    protected static int totalFreq=0;
    
    
    public void saveFreqTable(String fS){
    	StringBuilder sb=new StringBuilder();
    	String[] codes=fS.split(";");
    	String[] split;
       	for(int i=0;i<codes.length;i++){
    		split=codes[i].split(",");
    		freqTable[Integer.parseInt(split[0])]=Integer.parseInt(split[1]);
    		sb.append(Integer.parseInt(split[0])+","+Integer.parseInt(split[1])+";");
      		totalFreq+=Integer.parseInt(split[1]);
    	}   	
    }
    
   
    //*** Generating Huffman Tree
    public void generateHuffmanTree5324() throws IOException{
   	  while(minHeap.size5324()!=1){
   		  LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>> T1;
  		  
  		  LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>> T2;
  		  
  		  PQEntry5324<Integer,LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>> leaf1=  minHeap.removeMin5324();
  		  
  		  PQEntry5324<Integer,LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>> leaf2=  minHeap.removeMin5324();
  		
  		  LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>> T3=new LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>();
  		  // Inserting back in Min Heap
  		  T1=leaf1.getValue5324();
  		  T2=leaf2.getValue5324();
  		  
  		  int sumOfFreq=leaf1.getKey5324()+leaf2.getKey5324();
  		  
  		  NodeEntry5324<Integer,Integer> treeNode= new NodeEntry5324<Integer,Integer>(sumOfFreq,-1);
  		  
  		  T3.addRoot5324(treeNode);
  		  T3.attach5324(T3.getRoot5324(),T1, T2);
  		  minHeap.insert5324(sumOfFreq, T3); 
  		 }
  		 
   	  huffmanTree=minHeap.removeMin5324().getValue5324();
  	  
  	  //*** Gene
  	  //generateCode5324(huffmanTree.getRoot5324(),"");
  	  
   	  //*** Decode File
  	  DecodeFile(huffmanTree.getRoot5324());
  	  
    }
    
    
    //*** Inserting Frequencies in Freq Table with each as BT with one Node
    public  void insertFrequenciesInPQ5324(){
  	  for(int i=0;i<256;i++){
  		  if(freqTable[i]!=-1){
  		  LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>> temp= new LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>();
			   NodeEntry5324<Integer,Integer> tempNode=new NodeEntry5324<Integer,Integer>(freqTable[i],i);
			   temp.addRoot5324(tempNode);
			   minHeap.insert5324(freqTable[i],temp);
  		  }
  	  }
	}
    
    //**** Decoding file
    public void DecodeFile(BTNode5324<NodeEntry5324<Integer,Integer>> root) throws IOException{
	   	String outfile=decodefileName.substring(0, decodefileName.length()-4);
      	BTNode5324<NodeEntry5324<Integer,Integer>> temp=root;
      	BufferedInputStream bin=null;
      	BufferedOutputStream bout=null;
      	try{
	    	bin= new BufferedInputStream(new FileInputStream(decodefileName));
	    	bout= new BufferedOutputStream( new FileOutputStream(outfile));
           int i=0;
          
            while(bin.read()!='\n' || bin.read()!='\n');
	    	//** Reading actual bit strings
            int j = 0;
            while(true){ 
            	i=bin.read();
            	
            	// Getting first bit one at a time
            	for (int k = 128; k>0; k/=2){        
            	
	            	if((i & k) !=0){  // Going right
	     	            temp=temp.getRight5324();
	     	            
	     	        }else{ // Going Left
	     	            temp=temp.getLeft5324();
	     	        }
	  	            if(temp.getElement5324().getSymbol5324()!=-1 ){
	 	                bout.write(temp.getElement5324().getSymbol5324());//System.out.print(temp.data);
	 	                temp=root;
	 	                if (++j >= totalFreq)return;
	 	            }
            	}
	    	}// While loop ends
        }catch(Exception e){
            System.out.println("IO Error");
            e.printStackTrace();
        }finally{
        	bin.close();
        	bout.close();
        }
    	
    }
	public static void main(String args[]) throws IOException{

		  if (args.length < 1) {
	            System.out.println("Error!Please provide file name.");
	            System.out.println("Usage:");
	            System.out.println("java HuffmanDecoder <fileToDecompress>");
	            return;
	        }
	        if (!new File(args[0]).exists()) {
	            System.out.println("File not found. Please enter an existing file name.");
	            return;
	        }
	        
	    decodefileName=args[0];
	  //*** Checking if file is empty
	     File fr = new File(decodefileName);
	     if(fr.length()==0){
	    	 System.out.println("Error! File is Empty");
	    	 return;
	     }
	     //Checking if valid .huf extension
	     String extension=decodefileName.substring(decodefileName.length()-4);
	     if(!extension.equals(".huf")){
	    	 System.out.println("Error! Please provide .huf encoded file as input.");
	    	 return;
	     }
	     
	    System.out.println("Decoding Started! Please wait.....");
		hdec5324 hdec= new hdec5324();
		 //*** Initializing arrays to zero and null Respectively
	     for(int i=0;i<256;i++){
	    	 hdec.freqTable[i]=-1;
	     }
		//Reading Metadata First
		BufferedReader br=null;
		try{
			br= new BufferedReader( new InputStreamReader(new FileInputStream(decodefileName)));
			hdec.saveFreqTable(br.readLine());
					
		}catch(Exception e){
			System.out.println("IO Error");
			e.printStackTrace();
		}finally{
			br.close();
		}
		
		//**** Inserting frequencies in PQ
		hdec.insertFrequenciesInPQ5324();
		
		//*** Generating Huffman Tree
		hdec.generateHuffmanTree5324();
		
		System.out.println("Decoding Successful!");
		fr.delete();
	}
}
