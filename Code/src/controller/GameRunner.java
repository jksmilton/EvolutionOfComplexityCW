package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.DefaultMember;
import model.MemberInterface;
import model.Population;
import view.MultiDimView;
import view.OneDimGraphPanel;

public class GameRunner {

	private final static int SAMPLE_SIZE = 15, POP_SIZE = 25;
	
	public static void main(String[] args) {
		
		oneDimGame();
		greatestGapGame();
		smallestGapGame();
		greatestGapCrossover();
	}
	
	private static void greatestGapCrossover(){
		ArrayList<List<MemberInterface>> popOneHistory, popTwoHistory;
		popOneHistory = new ArrayList<List<MemberInterface>>();
		popTwoHistory = new ArrayList<List<MemberInterface>>();
		
		
		Population popControllerOne, popControllerTwo;
		
		List<MemberInterface> popOne, popTwo;
		popOne = createTenDim();
		popTwo = createTenDim();
		
		popControllerOne = new Population(popOne, 0, true);
		popControllerTwo = new Population(popTwo, 1, false);
		
		GreatestGapScorer scorer = new GreatestGapScorer(SAMPLE_SIZE, new Population[]{popControllerOne, popControllerTwo});
		popControllerOne.setScorer(scorer);
		popControllerTwo.setScorer(scorer);
		
		for(int i = 0; i < 600; i++){
			
			List<MemberInterface> newOne, newTwo;
			
			newOne = popControllerOne.evolvePopulation();
			newTwo = popControllerTwo.evolvePopulation();
			
			popOneHistory.add(newOne);
			popTwoHistory.add(newTwo);
			
			popControllerOne.setPopulation(newOne);
			popControllerTwo.setPopulation(newTwo);
			
		}
		
		popControllerOne.evolvePopulation();
		popControllerTwo.evolvePopulation();
		
		JFrame frame = new JFrame("Greatest Gap Crossover");
		
		MultiDimView panel = new MultiDimView(popOneHistory, popTwoHistory, SAMPLE_SIZE);
		
		frame.setBounds(50,50, 1000, 600);
		
		frame.setContentPane(panel);
		
		frame.setVisible(true);
	}
	
	private static void smallestGapGame(){
		ArrayList<List<MemberInterface>> popOneHistory, popTwoHistory;
		popOneHistory = new ArrayList<List<MemberInterface>>();
		popTwoHistory = new ArrayList<List<MemberInterface>>();
		
		
		Population popControllerOne, popControllerTwo;
		
		List<MemberInterface> popOne, popTwo;
		popOne = createTenDim();
		popTwo = createTenDim();
		
		popControllerOne = new Population(popOne, 0, false);
		popControllerTwo = new Population(popTwo, 1, false);
		
		SmallestGapScorer scorer = new SmallestGapScorer(SAMPLE_SIZE, new Population[]{popControllerOne, popControllerTwo});
		popControllerOne.setScorer(scorer);
		popControllerTwo.setScorer(scorer);
		
		for(int i = 0; i < 600; i++){
			
			List<MemberInterface> newOne, newTwo;
			
			newOne = popControllerOne.evolvePopulation();
			newTwo = popControllerTwo.evolvePopulation();
			
			popOneHistory.add(newOne);
			popTwoHistory.add(newTwo);
			
			popControllerOne.setPopulation(newOne);
			popControllerTwo.setPopulation(newTwo);
			
		}
		
		popControllerOne.evolvePopulation();
		popControllerTwo.evolvePopulation();
		
		JFrame frame = new JFrame("Smallest Gap");
		
		MultiDimView panel = new MultiDimView(popOneHistory, popTwoHistory, SAMPLE_SIZE);
		
		frame.setBounds(50,50, 1000, 600);
		
		frame.setContentPane(panel);
		
		frame.setVisible(true);
	}
	
	private static void greatestGapGame(){
		ArrayList<List<MemberInterface>> popOneHistory, popTwoHistory;
		popOneHistory = new ArrayList<List<MemberInterface>>();
		popTwoHistory = new ArrayList<List<MemberInterface>>();
		
		
		Population popControllerOne, popControllerTwo;
		
		List<MemberInterface> popOne, popTwo;
		popOne = createTenDim();
		popTwo = createTenDim();
		
		popControllerOne = new Population(popOne, 0, false);
		popControllerTwo = new Population(popTwo, 1, false);
		
		GreatestGapScorer scorer = new GreatestGapScorer(SAMPLE_SIZE, new Population[]{popControllerOne, popControllerTwo});
		popControllerOne.setScorer(scorer);
		popControllerTwo.setScorer(scorer);
		
		for(int i = 0; i < 600; i++){
			
			List<MemberInterface> newOne, newTwo;
			
			newOne = popControllerOne.evolvePopulation();
			newTwo = popControllerTwo.evolvePopulation();
			
			popOneHistory.add(newOne);
			popTwoHistory.add(newTwo);
			
			popControllerOne.setPopulation(newOne);
			popControllerTwo.setPopulation(newTwo);
			
		}
		
		popControllerOne.evolvePopulation();
		popControllerTwo.evolvePopulation();
		
		JFrame frame = new JFrame("Greatest Gap");
		
		MultiDimView panel = new MultiDimView(popOneHistory, popTwoHistory, SAMPLE_SIZE);
		
		frame.setBounds(50,50, 1000, 600);
		
		frame.setContentPane(panel);
		
		frame.setVisible(true);
	}
	
	private static void oneDimGame(){
		ArrayList<List<MemberInterface>> popOneHistory, popTwoHistory;
		popOneHistory = new ArrayList<List<MemberInterface>>();
		popTwoHistory = new ArrayList<List<MemberInterface>>();
		
		
		Population popControllerOne, popControllerTwo;
		
		List<MemberInterface> popOne, popTwo;
		popOne = createOneDim();
		popTwo = createOneDim();
		
		popControllerOne = new Population(popOne, 0, false);
		popControllerTwo = new Population(popTwo, 1, false);
		
		
		OneDimScorer scorer = new OneDimScorer(SAMPLE_SIZE, new Population[]{popControllerOne, popControllerTwo});
		
		popControllerOne.setScorer(scorer);
		popControllerTwo.setScorer(scorer);
		
		for(int i = 0; i < 600; i++){
			
			List<MemberInterface> newOne, newTwo;
			
			newOne = popControllerOne.evolvePopulation();
			newTwo = popControllerTwo.evolvePopulation();
			
			popOneHistory.add(newOne);
			popTwoHistory.add(newTwo);
			
			popControllerOne.setPopulation(newOne);
			popControllerTwo.setPopulation(newTwo);
			
		}
		
		popControllerOne.evolvePopulation();
		popControllerTwo.evolvePopulation();
		
		JFrame frame = new JFrame("One dimensional");
		
		OneDimGraphPanel panel = new OneDimGraphPanel(popOneHistory, popTwoHistory, SAMPLE_SIZE);
		
		frame.setBounds(50,50, 1000, 600);
		
		frame.setContentPane(panel);
		
		frame.setVisible(true);
	}

	private static List<MemberInterface> createOneDim(){
		
		ArrayList<MemberInterface> popOne;
		popOne = new ArrayList<MemberInterface>();
		
		
		
		for(int i = 0; i < POP_SIZE; i++){
			
			int[][] genomeOne = new int[1][];
			
			genomeOne[0] = new int[100];
			
			for(int j = 0; j < 100; j++){
				genomeOne[0][j] = 0;
			}
			
			popOne.add(new DefaultMember(genomeOne));
			
		}
		
		return popOne;
	}
	
	private static List<MemberInterface> createTenDim(){
		
		ArrayList<MemberInterface> popOne;
		popOne = new ArrayList<MemberInterface>();
		
		
		for(int i = 0; i < POP_SIZE; i++){
			
			int[][] genomeOne = new int[10][];
			
			for(int k = 0; k < 10; k++){
				genomeOne[k] = new int[10];
				
				for(int j = 0; j < 10; j++){
					genomeOne[k][j] = 0;
				}
			}
			popOne.add(new DefaultMember(genomeOne));
			
		}
		
		return popOne;
	}
	
}
