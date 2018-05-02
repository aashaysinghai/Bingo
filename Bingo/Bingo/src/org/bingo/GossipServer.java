package org.bingo;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
public class GossipServer
{
	
	public static int[][][] matrices;
  public static void main(String[] args) throws Exception
  {
      ServerSocket sersock = new ServerSocket(3000);
      System.out.println("Server  ready for chatting");
      Socket sock = sersock.accept( );                          
                              // reading from keyboard (keyRead object)
      BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
	                      // sending to client (pwrite object)
      ObjectOutputStream ostream = new ObjectOutputStream(sock.getOutputStream()); 

                              // receiving from server ( receiveRead  object)
      ObjectInputStream istream = new ObjectInputStream(sock.getInputStream());
      

      String receiveMessage, sendMessage;
      Message m;
      
      while(true)
      {
        if((m = (Message) istream.readObject()) != null)  
        {	
        	if("PLAY".equals(m.getMessage())) {
        		sendMessage = "Thank you for connecting to Bingo.";
        		BingoMatrix bingo = makeBingoMatrix();
                Message msg = new Message(sendMessage,bingo);
        		ostream.writeObject(msg);
        		ostream.flush();
        		istream = new ObjectInputStream(sock.getInputStream());
        		break;
        		
        	}
        }         
        
      }  
      // PLAY GAME........
      Map<Integer,Pair> player1 = new HashMap<>();
      Map<Integer,Pair> player2 = new HashMap<>();
      for(int i=0;i<3;i++) {
    	  for(int j=0;j<3;j++) {
    		  player1.put(matrices[0][i][j], new Pair(i,j));
    		  player2.put(matrices[1][i][j], new Pair(i,j));
    	  }
      }
      
      player1.entrySet().stream().forEach(System.out::println);
      player2.entrySet().stream().forEach(System.out::println);
      System.out.println();
      while(true) {
    	  if((m = (Message) istream.readObject()) != null)  {
    		  istream = new ObjectInputStream(sock.getInputStream());
    		  String pId = m.getPlayerId();
    		  String msg = m.getMessage();
    		  Integer num1 = Integer.parseInt(msg);
    		  Pair p1=player1.get(num1);
    		  Pair p2=player2.get(num1);
    		  matrices[0][p1.i][p1.j] = 0;
    		  matrices[1][p2.i][p2.j] = 0;
    		  printMatrix();
    		  if(checkForWin()) {
    			  break;
    		  }
    	  }
      }
      
      System.out.println("WOOOOOOOOOOOOON");
      
      
    }

	private static boolean checkForWin() {
		int sum1=0;
		int sum2=0;
		int Csum1=0;
		int Csum2=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				sum1+=matrices[0][i][j];
				sum2+=matrices[1][i][j];
				Csum1+=matrices[0][j][i];
				Csum2+=matrices[1][j][i];
			}
			if(sum1 == 0 || sum2 == 0 || Csum1== 0 || Csum2 == 0) {
				return true;
			}
		}
		return false;
	}

	private static BingoMatrix makeBingoMatrix() {
		matrices = MatrixLogic.makeThreeMatrix(2,3);
		BingoMatrix bm = new BingoMatrix();
		bm.setMatrix(matrices[0]);
		bm.setMessage("Welcome. Your player id is : 1");
		return bm;
	} 
	
	public static void printMatrix() {
		
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
					System.out.print(matrices[0][j][k] +" ");
				}
				System.out.println();
			}
			System.out.println("+++++++++++++++++++++++");
		}
	
} 

class Pair {
	public int i;
	public int j;
	public Pair(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
	@Override
	public String toString() {
		return "Pair [i=" + i + ", j=" + j + "]";
	}
}
