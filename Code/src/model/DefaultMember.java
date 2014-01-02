package model;

import java.lang.reflect.Array;

public class DefaultMember implements MemberInterface {

	public static double MUTATIONRATE = 0.005;
	
	private int subjectiveFitness;
	private int[][] genome;
	
	@Override
	public int compareTo(MemberInterface arg0) {
		
		return subjectiveFitness - arg0.getSubjectiveFitness();
		
	}

	@Override
	public int[] getMemberValue() {
		
		int dimensions = Array.getLength(genome);
		int[] usefulValue = new int[dimensions];
		int dimSize = Array.getLength(genome[0]);
		
		for(int i = 0; i < dimensions; i++){
			
			int count = 0;
			
			for(int j = 0; j < dimSize; j++){
				count+= genome[i][j];
			}
			
			usefulValue[i] = count;
			
		}
		
		return usefulValue;
	}

	@Override
	public int getSubjectiveFitness() {
		
		return subjectiveFitness;
	}

	@Override
	public void setSubjectiveFitness(int score) {
		subjectiveFitness = score;
		
	}

	@Override
	public MemberInterface evolveMember() {
		
		int dimensions = Array.getLength(genome);
		int dimSize = Array.getLength(genome[0]);
		
		int[][] newMemberGenome = new int[dimensions][];
		
		for(int i =0; i< dimensions; i++){
			newMemberGenome[i] = new int[dimSize];
			
			for(int j = 0; j < dimSize; j++){
				
				newMemberGenome[i][j] = genome[i][j];
				
				if(Math.random()<MUTATIONRATE) {
					
					newMemberGenome[i][j] = Math.abs(newMemberGenome[i][j] - 1);
					
				}
				
			}
			
		}
		
		return new DefaultMember(newMemberGenome);
	}
	
	public DefaultMember(int[][] newGenome){
		genome = newGenome;
	}

}
