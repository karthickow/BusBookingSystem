package com.bbss.button;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.bbss.util.DateChooser;
public class DateButton extends JButton
{
	private static final long serialVersionUID = 1L;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateChooser DATE_CHOOSER = new DateChooser((JFrame)null,"Select Date");
    private Date date;

    protected void fireActionPerformed( ActionEvent e ) {
	Date newDate = DATE_CHOOSER.select(date);
	if ( newDate == null )
	    return;
	setDate( newDate );
    }
    public DateButton( Date date ) {
	super( DATE_FORMAT.format(date) );
	this.date = date;
    }

    public DateButton() {
	this( new Date() );
    }
    public Date getDate() {
	return date;
    }
    public void setDate( Date date ) {
	Date old = this.date;
	this.date = date;
	setText( DATE_FORMAT.format(date) );
	firePropertyChange( "date", old, date );
    }
}
