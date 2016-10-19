package com.example.prora.demolearning2.adapter;

/**
 * Created by Dendimon on 12/17/2015.
 */
public class List2Line {
    private String label;
    private String summary;
	private int idImageView;
/*    private String id;
    private String count;*/
    public boolean isSelected;

    public List2Line(String Label, String Summary, int idImageView) {
        this.label = Label;
        this.summary = Summary;
	    this.idImageView = idImageView;
        this.isSelected = false;
    }

    public String getLabel(){return this.label;}

    public String getSummary(){return this.summary;}
    public void setSummanry(String summanry){this.summary = summanry;}

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

	public int getIdImageView() {
		return idImageView;
	}

	public void setIdImageView(int idImageView) {
		this.idImageView = idImageView;
	}
}
