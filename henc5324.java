/* SIDHU RAJVEER 	cs610 PrP 5324 */

import java.io.*;
public class henc5324{
	
	protected HeapPriorityQueue5324<Integer,LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>> minHeap = new HeapPriorityQueue5324<Integer,LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>>();
    protected Integer[] freqTable=new Integer[256];
	protected LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>> huffmanTree=new LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>();
	protected String[] codeTable=new String[256];
	protected static String fileName;
    
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
    	 //Sending generated Tree to generate Codes;
    	  PQEntry5324<Integer,LinkedBinaryTree5324<NodeEntry5324<Integer,Integer>>> generatedRoot;
    	  huffmanTree=minHeap.removeMin5324().getValue5324();
    	  
    	  //*** Generating codes
    	  
    	  generateCode5324(huffmanTree.getRoot5324(),"");
    	  
    	  //*** Compressing code
    	  Compress5324();
    	  
      }
    
      //*** Generating Codes from Huffman Tree
      public void generateCode5324(BTNode5324<NodeEntry5324<Integer,Integer>> node, String code){
    	 //System.out.println("Getting codes for characters");
    	 if(node.getLeft5324()==null && node.getRight5324()==null){
    		 codeTable[node.getElement5324().getSymbol5324()]= code;
    		 return;
 		 }
 		
 		if(node.getLeft5324()!=null){
 			generateCode5324(node.getLeft5324(),code+"0");
 		}
 		
 		if(node.getRight5324()!=null){
 			generateCode5324(node.getRight5324(),code+"1");
 		}
      }
     
      
     // **** Serializing Freq to string to write in as a file
      
      public StringBuilder serializeFreqToString(){
    	  StringBuilder freqString=new StringBuilder();
    	  for(int i=0;i<256;i++){
    		  if(freqTable[i]!=-1){
    			  freqString.append(i+","+freqTable[i]+";");
    		  }
    	  }
   
    	  return (freqString.append('\n').append('\n'));
      }
      
      //*** Compressing the file
      public void Compress5324() throws IOException{
    	  StringBuilder metaData=new StringBuilder();
    	  //*** Line 1. Appending Freq
    	  metaData=serializeFreqToString();
    	  // Writing metaData to file;
    	  try(BufferedOutputStream bout= new BufferedOutputStream( new FileOutputStream(fileName+".huf"))){
              for(int i=0;i<metaData.length();i++){
                  char c = metaData.charAt(i);
                  bout.write(c);
              }
              
              //** Replaces characters with bitstrings in file
             
              int i;
              String bitcode="";
              int tempwrite=0;
              int pos=7;
              BufferedInputStream bin= new BufferedInputStream( new FileInputStream(fileName));
              int k=0;
              do{
					i=bin.read();
					if(i!=-1){
						bitcode=codeTable[i];
						for(int j=0;j<bitcode.length();j++){
							char c= bitcode.charAt(j);
							if(c=='1'){
								tempwrite+=1<<pos;
							}
							pos--;
							if(pos<0){
								pos=7;
								bout.write(tempwrite);
								tempwrite=0;
							} //inner if ends
							
						} // for ends
						
					} // if ends
					k++;
				}while(i!=-1);
             
              if(pos!=7){
            	  bout.write(tempwrite);
              }
              
        }catch(Exception e){
              System.out.println("IO Error");
              e.printStackTrace();
        }
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
      
      //*** Main Function
		public static void main(String args[]) throws IOException{
			
			  if (args.length < 1) {
		            System.out.println("Error!Please provide file name.");
		            System.out.println("Example to run:");
		            System.out.println("HuffmanEncoder <fileToCompress>");
		            return;
		        }
		        if (!new File(args[0]).exists()) {
		            System.out.println("File not found. Please enter an existing file name.");
		            return;
		        }
		     fileName=args[0];
		     
		     //*** Checking if file is empty
		     File fr = new File(fileName);
		     if(fr.length()==0){
		    	 System.out.println("Error! File is Empty");
		    	 return;
		     }
		    
		    System.out.println("Encoding Started! Please wait.....");
			henc5324 henc= new henc5324();
			 //*** Initializing arrays to zero and null Respectively
		     for(int i=0;i<256;i++){
		    	 henc.freqTable[i]=-1;
		    	 henc.codeTable[i]=null;
		     }
			//** Saving frequencies in a table
			BufferedInputStream bin=null;
			int i=0;
			try{
				bin= new BufferedInputStream( new FileInputStream(fileName));
				do{
					i=bin.read();
					if(i!=-1){
						if(henc.freqTable[i]!=-1){
							Integer temp=henc.freqTable[i];
							henc.freqTable[i]=(temp+1);
						}else{
							henc.freqTable[i]=1;
						}
						
					}
				}while(i!=-1);
				
				//***  Adding to minHeap
								
				henc.insertFrequenciesInPQ5324();
				
				//*** Starting tree generation 
				
				henc.generateHuffmanTree5324();

			}catch(Exception e){
				System.out.println("IO Error");
				e.printStackTrace();
			}finally{
				bin.close();
			}
			System.out.println("Encoding Successful!");
			fr.delete();
		}
} // class ends
