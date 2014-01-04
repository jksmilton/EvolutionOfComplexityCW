package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import model.MemberInterface;

@SuppressWarnings("serial")
public class OneDimGraphPanel extends JPanel {

	private List<List<MemberInterface>> popOne, popTwo;
	private double sampleSize;
	public OneDimGraphPanel(List<List<MemberInterface>> popOne,	List<List<MemberInterface>> popTwo, int sampleSize) {
		super();
		this.popOne = popOne;
		this.popTwo = popTwo;
		this.sampleSize = (double) sampleSize;
	}

	@Override
	public void paint(Graphics g){
		
		super.paint(g);
	
		Graphics2D graphTwoDim = (Graphics2D) g;
		
		graphTwoDim.drawString("100", 30, 10);
		graphTwoDim.drawString("Objective", 2, 45);
		graphTwoDim.drawString("fitness", 2, 55);
		graphTwoDim.drawString("0", 35, 110);
		
		graphTwoDim.drawLine(60, 5, 660, 5);
		graphTwoDim.drawLine(60, 5, 60, 105);
		graphTwoDim.drawLine(60, 105, 660, 105);
		
		graphTwoDim.drawLine(60, 150, 60, 200);
		graphTwoDim.drawLine(60, 200, 660, 200);
		
		graphTwoDim.drawString("1", 35, 155);
		graphTwoDim.drawString("0", 35, 205);
		
		graphTwoDim.drawString("Subjective", 2, 175);
		graphTwoDim.drawString("fitness", 2, 185);
		
		graphTwoDim.drawString("0", 60, 120);
		graphTwoDim.drawString("600", 660, 120);
		graphTwoDim.drawString("Generation", 360, 120);
		double popSize = (double) popOne.get(0).size();
		for(int i = 0; i < 600; i++){
			
			int totalSubjectiveFitnessOne=0, totalSubjectiveFitnessTwo=0;
			
			for(int j = 0; j < (int) popSize; j++){
				
				int valOne = popOne.get(i).get(j).getMemberValue()[0];
				int valTwo = popTwo.get(i).get(j).getMemberValue()[0];
				
				graphTwoDim.setColor(Color.BLUE);				
				graphTwoDim.drawLine(61 + i, 105 - valOne, 61 + i, 105 - valOne);				
				
				graphTwoDim.setColor(Color.RED);
				graphTwoDim.drawLine(61 + i, 105 - valTwo, 61 + i, 105 - valTwo);
				
				totalSubjectiveFitnessOne += popOne.get(i).get(j).getSubjectiveFitness() - 1; //correct for scoring 'fix'		
				totalSubjectiveFitnessTwo += popTwo.get(i).get(j).getSubjectiveFitness() - 1;
				
			}
			
			double averageFitnessOne, averageFitnessTwo;
			
			
			
			averageFitnessOne = ((double) totalSubjectiveFitnessOne) / popSize;
			averageFitnessTwo = ((double) totalSubjectiveFitnessTwo) / popSize;
			
			averageFitnessOne = averageFitnessOne / sampleSize; //put between 0 and 1;
			averageFitnessTwo = averageFitnessTwo / sampleSize;
			
			int offsetOne, offsetTwo;
			
			offsetOne = (int) Math.round(averageFitnessOne * 50);
			offsetTwo = (int) Math.round(averageFitnessTwo * 50);
			
			graphTwoDim.setColor(Color.BLUE);				
			graphTwoDim.fillRect(59 + i, 198 - offsetOne, 4, 4);				
			
			graphTwoDim.setColor(Color.RED);
			graphTwoDim.fillRect(59 + i, 198 - offsetTwo, 4, 4);
			
		}
		
	}
	
	
	
}
