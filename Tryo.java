import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Tryo extends JFrame implements ActionListener{
	int turn=0;
	JButton[] b=new JButton[44];
	boolean win=false;
	boolean bempty=false;
	String message;
	int sd=0,vd=0,rd=0,kd;
	final int winCombo[][] = new int[][]    {
	        {1, 2, 3},                       {1, 5, 9},             {1, 6, 11},
	        {2, 3, 4},                       {5, 9, 13},            {2, 7, 12},
	        {5, 6, 7},                       {2, 6, 10},			{5,10,15},
	        {6, 7, 8},                       {6, 10, 14},           {6,11,16},
	        {9, 10, 11},                     {3, 7, 11},            {13,10,7},
	        {10, 11, 12},                    {7, 11,15 },			{14,11,8},
	        {13, 14, 15},                    {4, 8, 12},            {9,6,3},
	        {14, 15, 16},                    {8, 12, 16},           {10,7,4},     
	};
	boolean bClicked = false;
	int wonNumber1 = 1, wonNumber2 = 1, wonNumber3 = 1;
	final int S = 412, R = 268, V=163,color = 190;
	int k=1;
	
	public static void main(String[] args) throws Exception{
		new Tryo();
	}
	
    public Tryo(){
	    JFrame frame = new JFrame("Tryo");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new GridLayout(4,4,2,2));
	    frame.setSize(600,400);
	    frame.setLocationRelativeTo(null);
	    for(int i=1;i<=16;i++){
		   b[i]=new JButton();
		   b[i].setBackground(Color.WHITE);
		   b[i].setFont(new Font("Georgia",Font.BOLD,30));
		   b[i].addActionListener(this);
		   frame.add(b[i]);
		   frame.setVisible(true);
	    }
    }
    
    public void actionPerformed(ActionEvent e){
	  	for(int i=1; i<=16; i++){
		    if(e.getSource() == b[i]){
                	bClicked = true;
                	if(turn % 3 == 0){
	                	if(vd!=0){
		                	if(check(vd,i)){
		                		JOptionPane.showMessageDialog(null,"You cannot mark here.");
		            	    }
	              		   	else{
	              		   		if(b[i].getText().equals("")){
		              		   		b[i].setText("V"+"("+k+")");
		              		   		vd=i;
		              		   		turn++;
	              		   		}
	              		   		else{
	              		   			JOptionPane.showMessageDialog(null,"This position is already marked.");
	              		   		}
	              		   	}
	              	   	}   
          	   			else{
              		   		if(b[i].getText().equals("")){
	              		   		b[i].setText("V"+"("+k+")");
	              		   		vd=i;
	              		   		turn++;
              		   		}
              		   		else{
              		   			JOptionPane.showMessageDialog(null,"This position is already marked.");
              		   		}
           	   			} 
                	}
                	
                	
                	else if(turn % 3==1){
	                	if(rd!=0){
		                	if(check(rd,i)){
				       				JOptionPane.showMessageDialog(null,"You cannot mark here.");
				       			 }
				       			 else{
			              		   	if(b[i].getText().equals("")){
				              		   	b[i].setText("R"+"("+k+")");
				              		  	rd=i;
				              		  	turn++;
			              		   	}
			              		   	else{
			              		   		JOptionPane.showMessageDialog(null,"This position is already marked.");
			              		   	}
				       			 } 
				       	}
			    		else{
              		   		if(b[i].getText().equals("")){
	              		   		b[i].setText("R"+"("+k+")");
	              		   		rd=i;
	              		   		turn++;
              		   		}
              		   		else{
              		   			JOptionPane.showMessageDialog(null,"This position is already marked.");
              		   		}
			    		}
                	}

    
                	else {
	                	if(sd!=0){
		                	if(check(sd,i)){
								JOptionPane.showMessageDialog(null,"You cannot mark here.");
				   	     	}
							else{
	              		   		if(b[i].getText().equals("")){
		              		   		b[i].setText("S"+"("+k+")");
		              		   		sd=i;
		              		   		turn++;
		              		   		k++;
	              		   		}
	              		   		else{
	              		   			JOptionPane.showMessageDialog(null,"This position is already marked.");
	              		   		}
				     	    }
				     	}
						else{
              		   		if(b[i].getText().equals("")){
	              		   		b[i].setText("S"+"("+k+")");
	              		   		sd=i;
	              		   		turn++;
	              		   		k++;
              		   		}
              		   		else{
              		   			JOptionPane.showMessageDialog(null,"This position is already marked.");
              		   		}
						}
                	}
		    }
	 	}
	  if(bClicked){
		  checkWin();
		  bClicked = false;
	  }
  }
	    
    public void checkWin(){
		  	for(int i=0; i<24; i++){
		  		try{
			  		String sub1,sub2,sub3;
					wonNumber1 = winCombo[i][0];
					wonNumber2 = winCombo[i][1];
					wonNumber3 = winCombo[i][2];
					sub1 = b[wonNumber1].getText().substring(0,1);
					sub2 = b[wonNumber2].getText().substring(0,1);
					sub3 = b[wonNumber3].getText().substring(0,1);
					if(sub1.equals(sub2) && sub2.equals(sub3) && sub3.equals(sub1)){
						win = true;
						b[wonNumber1].setBackground(Color.white);
						b[wonNumber2].setBackground(Color.white);
						b[wonNumber3].setBackground(Color.white);
						break;
					}
		  		}
		  		catch(Exception Exp){}
			}
			if(win || (!win && turn>16)){
				if(win){
					 if(turn % 3 == 1)
						 message = "V has won!";
					 else if(turn % 3==2) 
						 message = "R has won!";
					 else
						 message = "S has won!";
					 win = false;
				}
				else if(!win && turn>16){
					message = "Both players have tied!\nBetter luck next time.";
				}
				JOptionPane.showMessageDialog(null, message);
				for(int i=1; i<=16; i++) {
					b[i].setEnabled(false);
				}
			}
	}
		
    public boolean check(int prev,int now){
			if(prev==1 || prev==4 || prev==13 || prev==16){
				if(prev==1){
					if(now==2 || now==5 || now==6) return true;
					else return false;
				}
				else if(prev==4){
					if(now==2 || now==7 || now==8) return true;
					else return false;
				}
				else if(prev==13){
					if(now==9 || now==10 || now==14) return true;
					else return false;
				}
				else if(prev==16){
					if(now==11 || now==12 || now==15) return true;
					else return false;
				}
			}
			else if(prev==2 || prev==3 || prev==5 || prev==8 || prev==9 || prev==12 || prev==14 || prev==15){
				if(prev==2){
					if(now==1 || now==3 || now==5 || now==6 || now==7) return true;
					else return false;
				}
				if(prev==3){
					if(now==2 || now==4 || now==6 || now==7 || now==8) return true;
					else return false;
				}
				if(prev==5){
					if(now==1 || now==2 || now==6 || now==9 || now==10) return true;
					else return false;
				}
				if(prev==8){
					if(now==2 || now==4 || now==7 || now==11 || now==12) return true;
					else return false;
				}
				if(prev==9){
					if(now==5 || now==6 || now==10 || now==13 || now==14) return true;
					else return false;
				}
				if(prev==12){
					if(now==7 || now==8 || now==11 || now==15 || now==16) return true;
					else return false;
				}
				if(prev==14){
					if(now==9 || now==10 || now==11 || now==13 || now==15) return true;
					else return false;
				}
				if(prev==15){
					if(now==10 || now==11 || now==12 || now==14 || now==16) return true;
					else return false;
				}
			}
			else{
				if(now==prev-1 || now==prev+1 || now==prev-3 || now==prev+3 || now==prev-4 || now==prev+4 || now==prev-5 || now==prev+5) return true;
				else return false;
			}
			return false;
	}
  }