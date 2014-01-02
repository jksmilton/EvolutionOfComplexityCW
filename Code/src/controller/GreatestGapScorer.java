package controller;

import java.util.Set;

import model.MemberInterface;
import model.Population;
import model.ScoringInterface;

public class GreatestGapScorer implements ScoringInterface {

	private int sampleSize;
	private Population[] populations;
	
	public GreatestGapScorer(int sampleSize, Population[] populations) {
		super();
		this.sampleSize = sampleSize;
		this.populations = populations;
	}
	
	@Override
	public int score(MemberInterface member, int parentPopulation) {
		int opposingPopulation;
		opposingPopulation = Math.abs(parentPopulation - 1);
		
		Set<MemberInterface> sample = populations[opposingPopulation].getSample(sampleSize);
		
		int score = 1;
		
		for(MemberInterface m : sample){
			
			int largestGap = -1, dimensionPicked = 0;
			
			for(int i = 0; i < 10; i++){
				int gap = Math.abs(m.getMemberValue()[i] - member.getMemberValue()[i]);
				
				if(gap > largestGap){
					largestGap = gap;
					dimensionPicked = i;
				}
				
			}
			
			if(member.getMemberValue()[dimensionPicked] >= m.getMemberValue()[dimensionPicked]){
				score++;
			}
			
		}
		
		return score;
	}

}
