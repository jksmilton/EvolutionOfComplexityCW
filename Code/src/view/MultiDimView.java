package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import model.MemberInterface;

@SuppressWarnings("serial")
public class MultiDimView extends JPanel {

	private List<List<MemberInterface>> popOne, popTwo;
	private double sampleSize;
	public MultiDimView(List<List<MemberInterface>> popOne,	List<List<MemberInterface>> popTwo, int sampleSize) {
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
		
		graphTwoDim.drawLine(60, 250, 60, 300);
		graphTwoDim.drawLine(60, 300, 660, 300);
		
		double popSize = (double) popOne.get(0).size();
		int[] highScoringSameDimCount = new int[600], numberHighScoring = new int[600];
		
		for(int i = 0; i < 600; i++){
			HashMap<Integer, Integer> count = setUpMap();
			int totalSubjectiveFitnessOne=0, totalSubjectiveFitnessTwo=0;
			numberHighScoring[i] = 0;
			for(int j = 0; j < popSize; j++){
				
				int valOne = tallyDimensions(popOne.get(i).get(j));
				int valTwo = tallyDimensions(popTwo.get(i).get(j));
				
				graphTwoDim.setColor(Color.BLUE);				
				graphTwoDim.drawLine(61 + i, 105 - valOne, 61 + i, 105 - valOne);				
				
				graphTwoDim.setColor(Color.RED);
				graphTwoDim.drawLine(61 + i, 105 - valTwo, 61 + i, 105 - valTwo);
				
				totalSubjectiveFitnessOne += popOne.get(i).get(j).getSubjectiveFitness() - 1; //correct for scoring 'fix'		
				totalSubjectiveFitnessTwo += popTwo.get(i).get(j).getSubjectiveFitness() - 1;
				
				if(popOne.get(i).get(j).getSubjectiveFitness() - 1 >= Math.round(sampleSize + 1 / 4)){
					numberHighScoring[i]++;
					count.put(getBestDimension(popOne.get(i).get(j)), count.get(getBestDimension(popOne.get(i).get(j))) + 1);
				}
				
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
			
			highScoringSameDimCount[i] = getBestCount(count);
			
		}
		double modifier = 50 / popSize;
		for(int i = 0; i < 599; i++){
			
			graphTwoDim.setColor(Color.BLUE);	
			graphTwoDim.drawLine(61 + i, (int) (300 - Math.round(numberHighScoring[i] * modifier)), 62 + i, (int) (300 - Math.round(numberHighScoring[i + 1] * modifier)));
			graphTwoDim.setColor(Color.RED);
			graphTwoDim.drawLine(61 + i, (int) (300 - Math.round(highScoringSameDimCount[i] * modifier)), 62 + i, (int) (300 - Math.round(highScoringSameDimCount[i + 1] * modifier)));
		}
		
	}
	
	private HashMap<Integer, Integer> setUpMap(){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i = 0; i<= 10; i++){
			map.put(i, 0);
		}
		
		return map;
	}
	
	private int getBestCount(HashMap<Integer, Integer> counts){
		
		int currentBest= -1;
		
		for(int i = 0; i < 10; i++){
			if(counts.get(i) > currentBest){
				currentBest = counts.get(i);
			}
		}
		//System.out.println("best count: " + currentBest);
		//return currentBest;
		return counts.get(10);
	}
	
	private int getBestDimension(MemberInterface member){
		int currentBestDim = -1, currentBest= -1;
		
		int[] values = member.getMemberValue();
		
		for(int i = 0; i < 10; i++){
			if(values[i] > currentBest){
				currentBest = values[i];
				currentBestDim = i;
			}
		}
		
		System.out.println(currentBest);
		
		return currentBest;
	}
	
	private int tallyDimensions(MemberInterface member){
		
		int objectiveFitness = 0;
		
		int[]values = member.getMemberValue();
		
		for(int v : values){
			
			objectiveFitness += v;
			
		}
		
		return objectiveFitness;
	}
	
}
