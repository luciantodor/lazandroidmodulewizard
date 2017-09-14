package org.lamw.appmodaldialogdemo1;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/*Draft java code by "Lazarus Android Module Wizard" [5/15/2017 22:57:41]*/
/*https://github.com/jmpessoa/lazandroidmodulewizard*/
/*jControl LAMW template*/

public class jModalDialog extends Activity {
	
    private long     pascalObj = 0;      // Pascal Object
    private Controls controls  = null;   // Control Class -> Java/Pascal Interface ...
    private Context  context   = null;
    
    Intent mI;
		
	private android.widget.RelativeLayout mLayout;	
		
	int lpH = RelativeLayout.LayoutParams.WRAP_CONTENT;
	int lpW = RelativeLayout.LayoutParams.MATCH_PARENT; //w
		
	public String mTitle;	
	public int mDlgTheme = android.R.style.Theme_Holo_Light_Dialog;
	
	int mHasWindowTitle = 0; 
	int mDlgType = 0;
	
    String[] mRequestInfoHint;
    String[] mRequestInfoText;
	String[] mRequestInfoFormat;
    String[] mRequestInfoInputType;
	EditText[] mEditInput;
	
	int mRequestInfoCount = 0;
	
	int mRequestCode = 1122;
	String mDialogTitle = "LAMW Modal Dialog Title";
	
	int mIndexAnchor;
	
	String mBtnOK = "Ok";
	String mBtnCancel = "Cancel";
	int mTitleFontSize = 0;
	String mHint = "Enter data";
    
    public jModalDialog() {    	
        super();  	 	      
    }
           
    //GUIDELINE: please, preferentially, init all yours params names with "_", ex: int _flag, String _hello ...
    public jModalDialog(Controls _ctrls, long _Self) { //Add more others news "_xxx" params if needed!
       //super(_ctrls.activity);
       context   = _ctrls.activity;
       pascalObj = _Self;
       controls  = _ctrls;             
    }
  
    public void jFree() {
      //free local objects...
      mI = null;
    }
    
    public void OpenDatePicker(int idx, int year, int month, int day) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            private int annonidx;
            private DatePickerDialog.OnDateSetListener init(int idx) {
                annonidx = idx;
                return this;
            }
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                SimpleDateFormat sdf = new SimpleDateFormat(mRequestInfoFormat[annonidx]);
                mEditInput[annonidx].setText(sdf.format(cal.getTime()));
            }
        }.init(idx);

        DatePickerDialog dialog=new DatePickerDialog(this, listener, year, month, day);
        dialog.show();
    }
    
    public void OpenTimePicker(int idx, int hour, int min, int sec) {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            private int annonidx;
            private TimePickerDialog.OnTimeSetListener init(int idx) {
                annonidx = idx;
                return this;
            }
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(0, 0, 0, hourOfDay, minute, 0);
                SimpleDateFormat sdf = new SimpleDateFormat(mRequestInfoFormat[annonidx]);
                mEditInput[annonidx].setText(sdf.format(cal.getTime()));
            }
        }.init(idx);

        TimePickerDialog dialog = new TimePickerDialog(this, listener, hour, min, true);
        dialog.show();
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        Intent intent = this.getIntent();
        
        mDlgTheme = intent.getIntExtra("dlg_theme", 0);        
        setTheme(mDlgTheme);
        
        mHasWindowTitle = intent.getIntExtra("dlg_has_window_title", 0);        
        mTitle = intent.getStringExtra("dlg_title");  //_dialogTitle
        mBtnOK =  intent.getStringExtra("dlg_btn_ok"); 
        mBtnCancel = intent.getStringExtra("dlg_btn_cancel"); 
        mTitleFontSize = intent.getIntExtra("dlg_font_title_size", 0);        
        mHint = intent.getStringExtra("dlg_inptu_hint");        
        mDlgType = intent.getIntExtra("dlg_type", 0); 
                        
        if (mHasWindowTitle == 1)            	
        	  requestWindowFeature(Window.FEATURE_NO_TITLE);  
           	    	        
        mRequestInfoCount = intent.getIntExtra("dlg_request_info_count", 0); 
       
        if (mRequestInfoCount > 0) { 
           mRequestInfoHint = new String[mRequestInfoCount];
           mRequestInfoText = new String[mRequestInfoCount];
           mRequestInfoFormat = new String[mRequestInfoCount];
           mRequestInfoInputType = new String[mRequestInfoCount];
           for (int i = 0; i < mRequestInfoCount;  i++) {        	           	  
              mRequestInfoHint[i] = intent.getStringExtra("dlg_ih" + String.valueOf(i));
              mRequestInfoText[i] = intent.getStringExtra("dlg_it" + String.valueOf(i));
              mRequestInfoFormat[i] = intent.getStringExtra("dlg_if" + String.valueOf(i));
              mRequestInfoInputType[i] = intent.getStringExtra("dlg_iit" + String.valueOf(i));
           }
        }
        
        mLayout = new android.widget.RelativeLayout(this);
        
        this.setContentView(mLayout);
                
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       
        /*
        WindowManager.LayoutParams lp = getWindow().getAttributes();        
        //When FLAG_DIM_BEHIND is set, this is the amount of dimming to apply. 
        //Range is from 1.0 for completely opaque to 0.0 for no dim. 
        lp.dimAmount = 0.5f;
       // lp.screenBrightness = 0.5F;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);         
        */
        
        this.setFinishOnTouchOutside(false);  // modal dialog
        
    }
    
    @Override
    public void onContentChanged() {    	
        
    	int screenWidth;
    	
        TextView title = new TextView(this);
        
        title.setId(1111);
        title.setPadding(20, 20, 20, 20);
        title.setText(mTitle);
        
        if (mTitleFontSize > 0)
           title.setTextSize(mTitleFontSize);
        
        android.widget.RelativeLayout.LayoutParams lparamstxt = new android.widget.RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);     // W,H
        
        lparamstxt.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lparamstxt.addRule(RelativeLayout.ALIGN_TOP);        
        title.setLayoutParams(lparamstxt);
            	    	   
        mLayout.addView(title, lparamstxt);
     
        if (mDlgType == 0) {  //inputBox
     	   
     	   mEditInput = new EditText[mRequestInfoCount]; //all edit inputs ...
     	   
     	   //Log.i("mRequestInfoCount", "count = "+ mRequestInfoCount);
    	   
           for (int j = 0;  j < mRequestInfoCount; j++) { //others inputs...
        	  mEditInput[j] = new EditText(this);
        	  mEditInput[j].setId(2222+j);
        	  mEditInput[j].setPadding(20, 30, 20, 30);      
        	  mEditInput[j].setText(mRequestInfoText[j]);
        	  mEditInput[j].setHint(mRequestInfoHint[j]);
              
              // Set input type
              String str = new String(mRequestInfoInputType[j]);
              if(str.equals("NUMBER")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_CLASS_NUMBER | 
                                           android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL | 
                                           android.text.InputType.TYPE_NUMBER_FLAG_SIGNED);
              } else if(str.equals("CURRENCY")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_CLASS_NUMBER | 
                                           android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL | 
                                           android.text.InputType.TYPE_NUMBER_FLAG_SIGNED);
              } else if (str.equals("CAPCHARACTERS")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
              } else if (str.equals("TEXT")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_CLASS_TEXT);
              } else if (str.equals("PHONE")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_CLASS_PHONE); 
              } else if (str.equals("PASSNUMBER")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                mEditInput[j].setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance()); 
              } else if (str.equals("PASSTEXT")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_CLASS_TEXT | 
                                           android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mEditInput[j].setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance()); 
              } else if (str.equals("TEXTMULTILINE")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_CLASS_TEXT | 
                                           android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);
              } else if (str.equals("DATE")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_NULL); // disable soft input
                mEditInput[j].setFocusable(false);
                mEditInput[j].setKeyListener(null);
                mEditInput[j].setOnClickListener(new View.OnClickListener() {
                    private int annonIdx;
                    private View.OnClickListener init(int idx) {
                        annonIdx = idx;
                        return this;
                    }
                    @Override
                    public void onClick(View v) {
                        final Calendar cal = Calendar.getInstance(java.util.Locale.getDefault());
                        int year = cal.get(java.util.Calendar.YEAR);
                        int month = cal.get(java.util.Calendar.MONTH);
                        int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
                        OpenDatePicker(annonIdx, year, month, day);
                    };
                }.init(j));
              } else if (str.equals("TIME")) {
                mEditInput[j].setInputType(android.text.InputType.TYPE_NULL); // disable soft input
                mEditInput[j].setFocusable(false);
                mEditInput[j].setKeyListener(null);
                mEditInput[j].setOnClickListener(new View.OnClickListener() {
                    private int annonIdx;
                    private View.OnClickListener init(int idx) {
                        annonIdx = idx;
                        return this;
                    }
                    @Override
                    public void onClick(View v) {
                        OpenTimePicker(annonIdx, 0, 0, 0);
                    };
                }.init(j));
              } else {
                mEditInput[j].setInputType(android.text.InputType.TYPE_CLASS_TEXT | 
                                           android.text.InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS );
              };
        	  
              android.widget.RelativeLayout.LayoutParams lparamsEdit = new android.widget.RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
        		                                                        RelativeLayout.LayoutParams.WRAP_CONTENT);     // W,H                
              lparamsEdit.addRule(RelativeLayout.CENTER_HORIZONTAL);      //parent 
              if (j == 0) {
                lparamsEdit.addRule(RelativeLayout.BELOW, title.getId());   //anchor
              } else {
                lparamsEdit.addRule(RelativeLayout.BELOW, mEditInput[j-1].getId());   //anchor        
              }
              mEditInput[j].setLayoutParams(lparamsEdit);                
              mLayout.addView(mEditInput[j]);
              
              mIndexAnchor  = j;
           }
           
        }
        
        DisplayMetrics metrics = getResources().getDisplayMetrics();        
        screenWidth = (int) (metrics.widthPixels * 0.45);
                 
        Button buttonOk = new Button(this);
        
        buttonOk.setId(3333);
        buttonOk.setPadding(20, 20, 20, 20);
        buttonOk.setText(mBtnOK);
        
        android.widget.RelativeLayout.LayoutParams lparamsOk = new android.widget.RelativeLayout.LayoutParams(screenWidth,
        		                                                  RelativeLayout.LayoutParams.WRAP_CONTENT);     // W,H                                               
        if  (mDlgType == 0) {   //inputBox
        	lparamsOk.addRule(RelativeLayout.ALIGN_PARENT_LEFT);              //parent
        	lparamsOk.addRule(RelativeLayout.BELOW, mEditInput[mIndexAnchor].getId());   //anchor
        }	

        if  (mDlgType > 0) {  //showmessage
        	
        	lparamsOk.addRule(RelativeLayout.BELOW, title.getId());   //anchor
        	
        	if (mDlgType == 1) 
        	  lparamsOk.addRule(RelativeLayout.CENTER_HORIZONTAL); //parent
        	
        	if (mDlgType == 2) 
          	  lparamsOk.addRule(RelativeLayout.ALIGN_PARENT_LEFT); //parent
        	        	
        }
        
        buttonOk.setLayoutParams(lparamsOk);                
        mLayout.addView(buttonOk);      
        
        buttonOk.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {                              
               Intent intent= new Intent();                
               
               //put the message to return as result in Intent                              
               if  (mDlgType == 0) {   //inputBox
                 intent.putExtra("REQUESTED_INFO_COUNT",  mRequestInfoCount);            	   
            	 for (int k=0; k < mRequestInfoCount; k++) {            		             		             				 
                     intent.putExtra(mRequestInfoHint[k], mEditInput[k].getText().toString());
            	 }            	             	 
               }
                 
               if  (mDlgType == 2) {   //Question
                   intent.putExtra("REQUESTED_INFO_COUNT",  1);
                   intent.putExtra("BUTTON", "OK");
               }
                              
               // Set The Result in Intent
               setResult(Activity.RESULT_OK, intent);
               // finish The activity 
               finish();    	
            }
        });
        
        
        if ( (mDlgType == 0)  || ((mDlgType == 2)) ){  //  == dlgInputBox,  dlgShowQuestion
        	
          Button buttonCancel = new Button(this);
          buttonCancel.setId(3334);
          buttonCancel.setPadding(20, 20, 20, 20);
          buttonCancel.setText(mBtnCancel);
        
          android.widget.RelativeLayout.LayoutParams lparamsCancel = new android.widget.RelativeLayout.LayoutParams(screenWidth,
        		                                                  RelativeLayout.LayoutParams.WRAP_CONTENT);     // W,H
        
          if (mDlgType == 0)
             lparamsCancel.addRule(RelativeLayout.BELOW, mEditInput[mIndexAnchor].getId());      //anchor
          
          if (mDlgType == 2)  //dlgQuestion
        	  lparamsCancel.addRule(RelativeLayout.BELOW, title.getId());   //anchor
          
          lparamsCancel.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);                //parent       
        
          buttonCancel.setLayoutParams(lparamsCancel);        
        
          mLayout.addView(buttonCancel);      
        
          buttonCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {                              
               Intent intent= new Intent();                
               // put the message to return as result in Intent  request_info_count                              
               intent.putExtra("REQUESTED_INFO_COUNT",  1);
               intent.putExtra("BUTTON", "CANCEL");
               // Set The Result in Intent                             
               setResult(Activity.RESULT_CANCELED,intent);
               // finish The activity 
               finish();    	
            }
           });
        }
    }
    
    private Class<?> GetClass(String _fullJavaclassName) {    	
 	    Class<?> cls = null;
 	    //String className = 'com.almondmendoza.library.openActivity';
 	    try {
 			cls = Class.forName(_fullJavaclassName);
 		} catch (ClassNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	    return cls; 		    	        	    
    }
            
    
    public String GetStringValue(Intent _intentData, String _fieldName) {
       return _intentData.getStringExtra(_fieldName);
    }
      
    public int GetIntValue(Intent _intentData, String _fieldName) {
       return _intentData.getIntExtra(_fieldName, 0);
    }
    
    
    public void SetRequestInfo(String[] _requestInfoHint) {
    	int count = _requestInfoHint.length;
    	
    	mRequestInfoHint = new String[count];    	
    	for(int i = 0; i < count; i++) {
    		 mRequestInfoHint[i] = _requestInfoHint[i];
    	}
    	
    }
    
    public void SetTheme(int _dialogTheme) {    	
    	switch(_dialogTheme) {
    	  case 0: mDlgTheme = android.R.style.Theme_Holo_Light_Dialog; break;
    	  case 1: mDlgTheme = android.R.style.Theme_Holo_Dialog; break;
    	  case 2: mDlgTheme = android.R.style.Theme_Dialog; break;
    	}      
    }
    
    public void SetHasWindowTitle(boolean _hasWindowTitle) {  
    	if (!_hasWindowTitle)
       	    mHasWindowTitle = 1;         	
    }
    
    public void SetDialogType(int _dialogType) {  //Pascal: TDialogType = (0=dlgInputBox, 1=dlgShowMessage);
  	    mDlgType = _dialogType;    	 
    }
        
    public void SetRequestCode(int _requestCode) {    	
    	mRequestCode = _requestCode;
    }
        
    public void SetDialogTitle(String _dialogTitle) {
    	 mDialogTitle = _dialogTitle;
    }
    
    //dlgShowMessage
    public void ShowMessage(String _packageName) {
     	Class<?> cls = GetClass(_packageName+"."+"jModalDialog");  //_javaClassName    	
     	if  (cls != null) {
     	   mI = new Intent();		
           mI.setClass(controls.activity, cls);
           
           mDlgType = 1; // force dlgShowMessage
           mI.putExtra("dlg_title", mDialogTitle);
           mI.putExtra("dlg_type", mDlgType);
           mI.putExtra("dlg_theme", mDlgTheme);
           mI.putExtra("dlg_has_window_title", mHasWindowTitle);
           mI.putExtra("dlg_btn_ok", mBtnOK);  //_dialogTitle
           mI.putExtra("dlg_btn_cancel", mBtnCancel);  //_dialogTitle
           mI.putExtra("dlg_font_title_size", mTitleFontSize);  
           
           mI.putExtra("dlg_request_info_count", 0);
           
           controls.activity.startActivity(mI);
     	}    	 
    }    
    
    public void InputForActivityResult(String _packageName, String[] _requestInfoHint, String[] _requestInfoText, String[] _requestInfoFormat, String[] _inputType) {
    	    	
    	int count;
    	
     	Class<?> cls = GetClass(_packageName+"."+"jModalDialog");  //_javaClassName
     	
     	if  (cls != null) {
     		
     	   mI = new Intent();
           mI.setClass(controls.activity, cls);
           
           mDlgType = 0; // force dlgInputBox
           
           mI.putExtra("dlg_title", mDialogTitle);           
           mI.putExtra("dlg_type", mDlgType);
           mI.putExtra("dlg_theme", mDlgTheme);
           mI.putExtra("dlg_has_window_title", mHasWindowTitle);

           mI.putExtra("dlg_btn_ok", mBtnOK);  //_dialogTitle
           mI.putExtra("dlg_btn_cancel", mBtnCancel);  //_dialogTitle
           mI.putExtra("dlg_font_title_size", mTitleFontSize);
           mI.putExtra("dlg_inptu_hint", mHint);
           
           count = _requestInfoHint.length;
                      
           mI.putExtra("dlg_request_info_count", count);           
           
       	   for(int i = 0; i < count; i++) {    		
   		      mI.putExtra("dlg_ih" + String.valueOf(i), _requestInfoHint[i]);
              mI.putExtra("dlg_it" + String.valueOf(i), _requestInfoText[i]);
              mI.putExtra("dlg_if" + String.valueOf(i), _requestInfoFormat[i]);
              mI.putExtra("dlg_iit" + String.valueOf(i), _inputType[i]);
   	       }
       	   
           controls.activity.startActivityForResult(mI, mRequestCode);
           
     	}
     	
    }

    //dlgShowQuestion
    public void QuestionForActivityResult(String _packageName) {
     	Class<?> cls = GetClass(_packageName+"."+"jModalDialog");  //_javaClassName    	
     	if  (cls != null) {
     	   mI = new Intent();		
           mI.setClass(controls.activity, cls);
           
           mDlgType = 2; // force dlgShowQuestion
           mI.putExtra("dlg_title", mDialogTitle);
           mI.putExtra("dlg_type", mDlgType);
           mI.putExtra("dlg_theme", mDlgTheme);
           mI.putExtra("dlg_has_window_title", mHasWindowTitle);
           
           mI.putExtra("dlg_btn_ok", mBtnOK);  
           mI.putExtra("dlg_btn_cancel", mBtnCancel);  
           mI.putExtra("dlg_font_title_size", mTitleFontSize);  

           mI.putExtra("dlg_request_info_count", 0);                     
           controls.activity.startActivityForResult(mI, mRequestCode);           
     	}    	 
    }    

    public void SetCaptionButtonOK(String _captionOk) {    	
    	mBtnOK = _captionOk;
    }

    public void SetCaptionButtonCancel(String _captionCancel) {    	
    	mBtnCancel = _captionCancel;
    }
    
    public void SetTitleFontSize (int _fontSize) {
    	mTitleFontSize = _fontSize; 
    }
    
    public void SetInputHint(String _hint) {
    	mHint = _hint;
    }

}

