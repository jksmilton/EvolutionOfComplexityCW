package controller;

import java.util.Set;

import model.MemberInterface;
import model.Population;
import model.ScoringInterface;

public class SmallestGapScorer implements ScoringInterface {

	private int sampleSize;
	private Population[] populations;
	
	public SmallestGapScorer(int sampleSize, Population[] populations) {
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
			
			int smallestGap = Integer.MAX_VALUE, dimensionPicked = 0;
			
			for(int i = 0; i < 10; i++){
				int gap = Math.abs(m.getMemberValue()[i] - member.getMemberValue()[i]);
				
				if(gap < smallestGap){
					smallestGap = gap;
					dimensionPicked = i;
				}
				
			}
			
			if(member.getMemberValue()[dimensionPicked] > m.getMemberValue()[dimensionPicked]){
				score++;
			}
			
		}
		
		return score;
	}

}
