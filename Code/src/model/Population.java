package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Population {

	private ScoringInterface scorer;
	private List<MemberInterface> currentPopulation;
	private int popNum;
	private boolean crossover;
	
	public Population(List<MemberInterface> initialPopulation, int popNumber, boolean doesCrossover){
		currentPopulation = initialPopulation;
		popNum = popNumber;
		crossover = doesCrossover;
	}
	
	public void setScorer(ScoringInterface scorer){
		this.scorer = scorer;
	}
	
	public Set<MemberInterface> getSample(int sampleSize){
		HashSet<MemberInterface> sample = new HashSet<MemberInterface>();
		Random selector = new Random();
		
		for(int i = 0; i < sampleSize; i++){
			
			boolean newElement = false;
			while(!newElement){
				int memberNum = selector.nextInt(currentPopulation.size());
				MemberInterface member = currentPopulation.get(memberNum);
				
				newElement = sample.add(member);
				
			}
			
		}
		
		return sample;
	}
	
	private int evaluatePopulation(){
		
		int totalScore = 0;
		
		for(MemberInterface member : currentPopulation){
			int subjectiveScore = scorer.score(member, popNum);
			member.setSubjectiveFitness(subjectiveScore);
			totalScore += subjectiveScore;
		}
		
		return totalScore;
		
	}
	
	public List<MemberInterface> evolvePopulation(){
		ArrayList<MemberInterface> newPopulation = new ArrayList<MemberInterface>();
		
		int totalScore = evaluatePopulation();
		Collections.sort(currentPopulation);
		
		for(int i =0; i < currentPopulation.size(); i++){
			int member = fitnessProportionateSelect(totalScore);
			
			MemberInterface memberToEvolve = currentPopulation.get(member);
			
			if(crossover){
				int partner;
				do{
					partner = fitnessProportionateSelect(totalScore);
				} while(partner != member);
				
				memberToEvolve = crossoverMembers(memberToEvolve, currentPopulation.get(partner));
				
			}
			
			newPopulation.add(currentPopulation.get(member).evolveMember());			
		}
		
		return newPopulation;
	}
	
	public void setPopulation(List<MemberInterface> newPop){
		currentPopulation = newPop;
	}
	
	private MemberInterface crossoverMembers(MemberInterface parentOne, MemberInterface parentTwo){
		
		int[][] genome;
		int[][] parentOneGenome = parentOne.getMemberGenome();
		int[][] parentTwoGenome = parentTwo.getMemberGenome();
		
		int numDimensions = Array.getLength(parentOneGenome); //doesn't matter which parent is used
		
		genome = new int[numDimensions][]; 
		
		for(int i = 0; i < numDimensions; i++){
			
			if( 0 != i % 2){
				
				genome[i] = parentOneGenome[i].clone();
				
			} else {
				
				genome[i] = parentTwoGenome[i].clone();
				
			}
			
		}
		
		return new DefaultMember(genome);
		
	}
	
	private int fitnessProportionateSelect(int totalScore){
		
		Random picker = new Random();
		int pick = picker.nextInt(totalScore) + 1;
		
		int cummulativeScore = 0;
		int currentMember = -1;
		
		while(cummulativeScore < pick){
			currentMember ++;
			cummulativeScore += currentPopulation.get(currentMember).getSubjectiveFitness();
		}
		
		return currentMember;
	}
	
}
