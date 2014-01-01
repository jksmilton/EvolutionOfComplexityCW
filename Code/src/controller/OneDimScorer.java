package controller;

import java.util.Set;

import model.MemberInterface;
import model.Population;
import model.ScoringInterface;

public class OneDimScorer implements ScoringInterface {

	private int sampleSize;
	private Population[] populations;
	
	public OneDimScorer(int sampleSize, Population[] populations) {
		super();
		this.sampleSize = sampleSize;
		this.populations = populations;
	}
	
	
	@Override
	public int score(MemberInterface member, int parentPopulation) {
		
		int opposingPopulation;
				
		opposingPopulation = Math.abs(parentPopulation - 1);
		Set<MemberInterface> sample = populations[opposingPopulation].getSample(sampleSize);
		
		int score = 0;
		
		for(MemberInterface m : sample){
			if(m.getMemberValue()[0] < member.getMemberValue()[0]){
				score++;
			}
		}
		
		return score;
	}

}
