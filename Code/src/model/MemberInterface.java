package model;

public interface MemberInterface extends Comparable<MemberInterface>{

	public int[][] getMemberValue();
	public int getObjectiveFitness();
	public int getSubjectiveFitness();
	public void setSubjectiveFitness(int score);
	public MemberInterface evolveMember();
	
}
