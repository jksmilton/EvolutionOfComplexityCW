package model;

public interface MemberInterface extends Comparable<MemberInterface>{

	public int[] getMemberValue();
	public int getSubjectiveFitness();
	public void setSubjectiveFitness(int score);
	public MemberInterface evolveMember();
	public int[][] getMemberGenome();
}
