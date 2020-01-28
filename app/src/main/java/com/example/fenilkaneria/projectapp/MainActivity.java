package com.example.fenilkaneria.projectapp;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.SharedPreferences;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import android.widget.Toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {



    TextView statusText;
    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;

    ImageButton buttonSet;
    boolean buttonCount1=false;
    boolean buttonCount2=false;
    boolean buttonCount3=false;
    boolean buttonCount4=false;

    RadioButton checkBox1;
    RadioButton checkBox2;
    RadioButton checkBox3;
    RadioButton checkBox4;
    RadioButton checkBoxAll;

    RadioGroup radioGroup1;

    RadioButton radioButtonOn;
    RadioButton radioButtonOff;
    RadioButton radioButtonReset;

    RadioButton radioButtonOnTime;
    RadioButton radioButtonTimer;

    RadioButton radioButtonAm;
    RadioButton radioButtonPm;


    EditText editTextHour;
    EditText editTextMinute;




    boolean statusTextbool=false;


    String timeStringF="";
    String timeStringFF="";
    String timeStringFinal="";
    String timeStringP1="";
    String timeStringP2="";
    String timeStringP3="";
    String timeStringP4="";
    String timeStringP5="";
    String timeStringP6="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        SharedPreferences sp = getSharedPreferences("Storage", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        buttonCount1= sp.getBoolean("buttonCount1", false);
        buttonCount2= sp.getBoolean("buttonCount2", false);
        buttonCount3= sp.getBoolean("buttonCount3", false);
        buttonCount4= sp.getBoolean("buttonCount4", false);







        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);

        checkconection();

        buttonSet=findViewById(R.id.buttonSet);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        initializeButton();


        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBoxAll=findViewById(R.id.checkBoxAll);

        radioGroup1=findViewById(R.id.radioGroup1);

        radioButtonOn=findViewById(R.id.radioButtonOn);
        radioButtonOff=findViewById(R.id.radioButtonOff);
        radioButtonReset=findViewById(R.id.radioButtonReset);

        radioButtonOnTime=findViewById(R.id.radioButtonOnTime);
        radioButtonTimer=findViewById(R.id.radioButtonTimer);


        radioButtonAm=findViewById(R.id.radioButtonAm);
        radioButtonPm=findViewById(R.id.radioButtonPm);


        editTextHour=findViewById(R.id.editTextHour);
        editTextMinute=findViewById(R.id.editTextMinute);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!buttonCount1) {

                    sendMessage("~~~~~!!!!!",1,2);
                    Log.d("==0", "pressed");
                   // sendMessage("%%%");

                } else {
                    sendMessage("~~~~~%%%%%",1,2);

                    Log.d("==1", "pressed");


                }
            }
        });

       b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!buttonCount2) {
                    sendMessage("~~~~~bbbbb",2,2);
                } else {
                    sendMessage("~~~~~AAAAA",2,2);
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!buttonCount3) {
                    sendMessage("~~~~~TTTTT",3,2);
                } else {
                    sendMessage("~~~~~XXXXX",3,2);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!buttonCount4) {
                    sendMessage("~~~~~vvvvv",4,2);
                } else {
                    sendMessage("~~~~~zzzzz",4,2);
                }
            }
        });




        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTimeMessage();
                //Log.d("cc", "clicked");

            }
        });


        radioButtonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioButtonAm.setEnabled(false);
                if (Build.VERSION.SDK_INT > 21) { radioButtonAm.setButtonTintList(ColorStateList.valueOf(Color.rgb(209, 209, 209)));}
                radioButtonAm.setTextColor(Color.rgb(209, 209, 209));

                radioButtonPm.setEnabled(false);
                if (Build.VERSION.SDK_INT > 21) { radioButtonPm.setButtonTintList(ColorStateList.valueOf(Color.rgb(209, 209, 209)));}
                radioButtonPm.setTextColor(Color.rgb(209, 209, 209));

            }
        });

        radioButtonOnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioButtonAm.setEnabled(true);
                    if (Build.VERSION.SDK_INT > 21) { radioButtonAm.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                radioButtonAm.setTextColor(Color.rgb(58, 115, 142));

                radioButtonPm.setEnabled(true);
                        if (Build.VERSION.SDK_INT > 21) { radioButtonPm.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                radioButtonPm.setTextColor(Color.rgb(58, 115, 142));
            }
        });

        radioButtonReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                radioButtonOnTime.setEnabled(false);
                if (Build.VERSION.SDK_INT > 21) { radioButtonOnTime.setButtonTintList(ColorStateList.valueOf(Color.rgb(209, 209, 209)));}
                radioButtonOnTime.setTextColor(Color.rgb(209, 209, 209));

                radioButtonTimer.setEnabled(false);
                    if (Build.VERSION.SDK_INT > 21) { radioButtonTimer.setButtonTintList(ColorStateList.valueOf(Color.rgb(209, 209, 209)));}
                 radioButtonTimer.setTextColor(Color.rgb(209, 209, 209));

                editTextMinute.setEnabled(false);
                editTextMinute.setTextColor(Color.rgb(209, 209, 209));


                editTextHour.setEnabled(false);
                editTextHour.setTextColor(Color.rgb(209, 209, 209));


                radioButtonAm.setEnabled(false);
                        if (Build.VERSION.SDK_INT > 21) { radioButtonAm.setButtonTintList(ColorStateList.valueOf(Color.rgb(209, 209, 209)));}
                radioButtonAm.setTextColor(Color.rgb(209, 209, 209));

                radioButtonPm.setEnabled(false);
                            if (Build.VERSION.SDK_INT > 21) { radioButtonPm.setButtonTintList(ColorStateList.valueOf(Color.rgb(209, 209, 209)));}
                radioButtonPm.setTextColor(Color.rgb(209, 209, 209));

            }
        });
        radioButtonOn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                radioButtonOnTime.setEnabled(true);
                if (Build.VERSION.SDK_INT > 21) { radioButtonOnTime.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                radioButtonOnTime.setTextColor(Color.rgb(58, 115, 142));

                radioButtonTimer.setEnabled(true);
                    if (Build.VERSION.SDK_INT > 21) { radioButtonTimer.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
               radioButtonTimer.setTextColor(Color.rgb(58, 115, 142));

                editTextMinute.setEnabled(true);
                editTextMinute.setTextColor(Color.rgb(58, 115, 142));

                editTextHour.setEnabled(true);
                editTextHour.setTextColor(Color.rgb(58, 115, 142));

               if(!radioButtonTimer.isChecked())
                {radioButtonAm.setEnabled(true);
                    if (Build.VERSION.SDK_INT > 21) { radioButtonAm.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                radioButtonAm.setTextColor(Color.rgb(58, 115, 142));

                radioButtonPm.setEnabled(true);
                        if (Build.VERSION.SDK_INT > 21) { radioButtonPm.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                radioButtonPm.setTextColor(Color.rgb(58, 115, 142));}

            }
        });
        radioButtonOff.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                radioButtonOnTime.setEnabled(true);
                if (Build.VERSION.SDK_INT > 21) { radioButtonOnTime.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                radioButtonOnTime.setTextColor(Color.rgb(58, 115, 142));

                radioButtonTimer.setEnabled(true);
                    if (Build.VERSION.SDK_INT > 21) {  radioButtonTimer.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                radioButtonTimer.setTextColor(Color.rgb(58, 115, 142));

                editTextMinute.setEnabled(true);
                editTextMinute.setTextColor(Color.rgb(58, 115, 142));

                editTextHour.setEnabled(true);
                editTextHour.setTextColor(Color.rgb(58, 115, 142));


                if(!radioButtonTimer.isChecked())
                {radioButtonAm.setEnabled(true);
                    if (Build.VERSION.SDK_INT > 21) {  radioButtonAm.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                    radioButtonAm.setTextColor(Color.rgb(58, 115, 142));

                    radioButtonPm.setEnabled(true);
                        if (Build.VERSION.SDK_INT > 21) {  radioButtonPm.setButtonTintList(ColorStateList.valueOf(Color.rgb(58, 115, 142)));}
                    radioButtonPm.setTextColor(Color.rgb(58, 115, 142));}

            }
        });



    }




    private final void sendMessage(final String msg,final int buttonNum,final int count)  {

        //   final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {



                    /*for speed************/
                   statusText.setText("not connected");
                    statusText.setBackgroundResource(R.color.ststusRed);
                    statusTextbool=false;
                    /*********************/


                    Socket s = new Socket("192.168.4.1", 333);
                    Thread.sleep(30);

                    /*for speed************/
                    statusText.setText("connected");
                    statusText.setBackgroundResource(R.color.ststusGreen);
                    statusTextbool=true;

                    /*********************/

                    setBotton(buttonNum);


                    OutputStream out = s.getOutputStream();

                    PrintWriter output = new PrintWriter(out);



                    Thread.sleep(30);

                    output.println(msg);





                    Thread.sleep(30);

                    output.flush();
                    output.close();
                    out.close();
                    s.close();


                     for(int i=0;i<count;i++)
                     {
                     Thread.sleep(30);
                    s = new Socket("192.168.4.1", 333);
                    Thread.sleep(30);
                    out = s.getOutputStream();
                    output = new PrintWriter(out);
                    Thread.sleep(30);
                    output.println(msg);
                    Thread.sleep(30);
                    output.flush();
                    output.close();
                    out.close();
                    s.close();
                     }



                    //---------------------*/


                } catch (UnknownHostException e) {
                    //setStatusText(false);
                    statusTextbool=false;
                    e.printStackTrace();
                    Log.d("E", "Exception1");
                } catch (IOException e) {
                   // setStatusText(false);
                    statusTextbool=false;
                    e.printStackTrace();
                    Log.d("E", "Exception2");
                } catch (InterruptedException e) {
                   // setStatusText(false);
                    statusTextbool=false;
                    e.printStackTrace();
                    Log.d("E", "Exception3");
                } catch (Exception e) {
                   // setStatusText(false);
                    statusTextbool=false;
                    e.printStackTrace();
                    Log.d("E", "Exception4");
                }



            }
        });

        thread.start();

        }






    void initializeButton()

    {
        if (buttonCount1) {
            b1.setImageResource(R.drawable.buttonon);


        } else {
            b1.setImageResource(R.drawable.buttonoff);

        }

        if (buttonCount2) {
            b2.setImageResource(R.drawable.buttonon);


        } else {
            b2.setImageResource(R.drawable.buttonoff);

        }

        if (buttonCount3) {
            b3.setImageResource(R.drawable.buttonon);


        } else {
            b3.setImageResource(R.drawable.buttonoff);

        }

        if (buttonCount4) {
            b4.setImageResource(R.drawable.buttonon);


        } else {
            b4.setImageResource(R.drawable.buttonoff);

        }


    }

    void setBotton(int buttonNum)
    {

        if (buttonNum == 1) {
            if (!buttonCount1) {
                b1.setImageResource(R.drawable.buttonon);
                buttonCount1 = true;
            } else {
                b1.setImageResource(R.drawable.buttonoff);
                buttonCount1 = false;
            }
        }

        else if (buttonNum == 2) {
            if (!buttonCount2) {
                b2.setImageResource(R.drawable.buttonon);
                buttonCount2 = true;
            } else {
                b2.setImageResource(R.drawable.buttonoff);
                buttonCount2 = false;
            }
        }

        else if (buttonNum == 3) {
            if (!buttonCount3) {
                b3.setImageResource(R.drawable.buttonon);
                buttonCount3 = true;
            } else {
                b3.setImageResource(R.drawable.buttonoff);
                buttonCount3 = false;
            }
        }

        else if (buttonNum == 4) {
            if (!buttonCount4) {
                b4.setImageResource(R.drawable.buttonon);
                buttonCount4 = true;
            } else {
                b4.setImageResource(R.drawable.buttonoff);
                buttonCount4 = false;
            }
        }

    }


    private final void checkconection()

    {     sendMessage("",0,0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                checkconection();
               // setStatusText();
                return (true);


            case R.id.exit:
                finish();
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }

    void setStatusText() {
        if (statusTextbool) {
            statusText.setText("connected");
            statusText.setBackgroundResource(R.color.ststusGreen);
        } else {

            statusText.setText("not connected");
            statusText.setBackgroundResource(R.color.ststusRed);
        }


    }

    void setStatusTextNew(){
        statusText.setText("not connected");
        statusText.setBackgroundResource(R.color.ststusRed);

    }



    @Override
    public void onStop() {
        SharedPreferences sp = getSharedPreferences("Storage", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("buttonCount1",buttonCount1);
       edit.putBoolean("buttonCount2",buttonCount2);
       edit.putBoolean("buttonCount3",buttonCount3);
       edit.putBoolean("buttonCount4",buttonCount4);
        edit.apply();

        super.onStop();

    }
   //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

   void setTimeStringP2(){


       if(checkBox1.isChecked())
        timeStringP2="1";
       else if(checkBox2.isChecked())
           timeStringP2="2";
       else if(checkBox3.isChecked())
           timeStringP2="3";
       else if(checkBox4.isChecked())
           timeStringP2="4";
       else if(checkBoxAll.isChecked())
           timeStringP2="5";

   }

    void setTimeStringP1(){


        if(radioButtonOn.isChecked())
        { timeStringP1="n";}
        else if(radioButtonOff.isChecked())
        { timeStringP1="f";}
        else if(radioButtonReset.isChecked())
        {timeStringP1="r";}

    }
    void setTimeStringP3(){
        if(radioButtonPm.isChecked() && editTextHour.getText().toString().length()>0 && !radioButtonTimer.isChecked())
        {
            timeStringP3=editTextHour.getText().toString();
         int x=Integer.parseInt(timeStringP3);
           x=x+12;

           timeStringP3=String.valueOf(x);

        }

        else if (radioButtonAm.isChecked() && editTextHour.getText().toString().length()>0 && !radioButtonTimer.isChecked()){

            timeStringP3=editTextHour.getText().toString();
            int x=Integer.parseInt(timeStringP3);

            if (x==12){x=0;}
            if(x<10)
            {
                timeStringP3="0"+String.valueOf(x);
            }
            else
            {
                timeStringP3=String.valueOf(x);
            }

        }

        else if (radioButtonTimer.isChecked() && editTextHour.getText().toString().length()>0){

            timeStringP3=editTextHour.getText().toString();
            int x=Integer.parseInt(timeStringP3);

                timeStringP3=String.valueOf(x);


        }

    }

    void setTimeStringP4(){
        if(editTextMinute.getText().toString().length()>0)
        timeStringP4=editTextMinute.getText().toString();

        int x=Integer.parseInt(timeStringP4);

        if(x<10)
        {
            timeStringP4="0"+String.valueOf(x);
        }
        else
        {
            timeStringP4=String.valueOf(x);
        }




    }


    void setTimeStringP5()
    {   int count=0;
        char []c=timeStringF.toCharArray();

        Log.d("lengthp5",timeStringF);
          Log.d("lengthp5",String.valueOf(c.length));
        for(int i=1;i<c.length;i++)
        {
           if( (Character.getNumericValue(c[i])%2)!=0 )
           {
               count++;
           }

        }

        timeStringP5=String.valueOf(count);

    }

    void setTimeStringP6()
    {   int count=0;
        char []c=timeStringFF.toCharArray();
        Log.d("lengthp6",String.valueOf(c.length));

        for(int i=1;i<c.length;i++)
        {

                count=count+(Character.getNumericValue(c[i]));


        }

        if(count<10)
        {
            timeStringP6="0"+String.valueOf(count);
        }
        else
        {
            timeStringP6=String.valueOf(count);
        }




    }


    void sendTimeMessage() {

        checkconection();

        if (statusTextbool) {

            setTimeStringP1();

            if(timeStringP1=="r")
            {
                if (checkBox1.isChecked()) {
                    sendMessage("r1123",0,5);
                    Toast.makeText(MainActivity.this,
                            "Successfully Time Set", Toast.LENGTH_LONG).show();
                }
                else if (checkBox2.isChecked()) {

                    sendMessage("r2123",0,5);
                    Toast.makeText(MainActivity.this,
                            "Successfully Time Set", Toast.LENGTH_LONG).show();

                }
               else if (checkBox3.isChecked()) {

                    sendMessage("r3123",0,5);
                    Toast.makeText(MainActivity.this,
                            "Successfully Time Set", Toast.LENGTH_LONG).show();

                }
                else if (checkBox4.isChecked()) {

                    sendMessage("r4123",0,5);
                    Toast.makeText(MainActivity.this,
                            "Successfully Time Set", Toast.LENGTH_LONG).show();
                }
                else if (checkBoxAll.isChecked()) {

                    sendMessage("r5123", 0, 5);
                    Toast.makeText(MainActivity.this,
                            "Successfully Time Set", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,
                            "Please Select Devices", Toast.LENGTH_LONG).show();

                }

            }
           else {

                if (radioButtonOnTime.isChecked()) {



                    if(editTextHour.getText().toString().length()>0 && editTextMinute.getText().toString().length()>0 ) {

                        setTimeStringP2();
                        setTimeStringP3();
                        setTimeStringP4();
                        int hourCompare=0;
                        String timerD="d";
                        if(radioButtonAm.isChecked()){hourCompare=12;}else{hourCompare=24;}

                        if (checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked()|| checkBoxAll.isChecked()) {

                        if (Integer.parseInt(timeStringP3) > hourCompare) {
                            Toast.makeText(MainActivity.this,
                                    "Hour Should Be Less Or Equal To 12", Toast.LENGTH_LONG).show();
                        }

                        else if (Integer.parseInt(timeStringP4) > 59) {
                            Toast.makeText(MainActivity.this,
                                    "Minute Should Be Less Or Equal To 59", Toast.LENGTH_LONG).show();
                        } else{



                            timeStringF = timeStringP1 + timeStringP2 + timeStringP3 + timeStringP4;
                            setTimeStringP5();
                            timeStringFF = timeStringF + timeStringP5;
                            setTimeStringP6();
                            timeStringFinal = timerD +timeStringFF + timeStringP6;
                            Log.d("timeStringP31", timeStringP3);
                            Log.d("timeStringP32", String.valueOf(Integer.parseInt(timeStringP3)));



                                if (timeStringP1.length() > 0 && timeStringP2.length() > 0 && timeStringP3.length() > 0 && timeStringP4.length() > 0 && timeStringP5.length() > 0 && timeStringP6.length() > 0) {

                                    sendMessage(timeStringFinal, 0, 20);
                                    Toast.makeText(MainActivity.this,
                                            "Successfully Time Set", Toast.LENGTH_LONG).show();


                                } else {
                                    Toast.makeText(MainActivity.this,
                                            "Please Fill All Data First", Toast.LENGTH_LONG).show();
                                }





                    }
                        }
                         else{
                            Toast.makeText(MainActivity.this,
                                    "Please Select Devices", Toast.LENGTH_LONG).show();

                        }


                    }else{Toast.makeText(MainActivity.this,
                            "Please Fill All Data First", Toast.LENGTH_LONG).show();}

                }

                else if (radioButtonTimer.isChecked()) {

                        if(editTextHour.getText().toString().length()>0 && editTextMinute.getText().toString().length()>0 )
                        {
                            setTimeStringP2();
                        setTimeStringP3();
                    setTimeStringP4();

                    Date currentTime = Calendar.getInstance().getTime();

                            String timerJ="j";
                            int hour=Integer.parseInt(timeStringP3);
                            int minute=Integer.parseInt(timeStringP4);

                           Log.d("Timerhour", String.valueOf(hour));
                            Log.d("Timerminute", String.valueOf(minute));

                            if (checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked() || checkBoxAll.isChecked()){

                             if(hour>24){Toast.makeText(MainActivity.this,
                            "Hour Should Be Less Or Equal To 24", Toast.LENGTH_LONG).show();}

                            else if(minute>60){Toast.makeText(MainActivity.this,
                                    "Minute Should Be Less Or Equal To 60", Toast.LENGTH_LONG).show();}


                    else
                    {
                        hour=currentTime.getHours();
                        minute=currentTime.getMinutes();
                        Log.d("Timerhour", String.valueOf(hour));
                        Log.d("Timerminute", String.valueOf(minute));


                        hour=Integer.parseInt(timeStringP3)+hour;
                        minute=Integer.parseInt(timeStringP4)+minute;

                        Log.d("Timerhour", String.valueOf(hour));
                        Log.d("Timerminute", String.valueOf(minute));


                    if(minute>59)
                    {
                        hour=hour+(minute/60);
                        minute=minute%60;

                     }
                        Log.d("Timerhour!!!!", String.valueOf(hour));
                    if(hour>23)
                    {

                           hour= hour-24;

                    }
                        if(hour<10)
                        {
                            timeStringP3="0"+String.valueOf(hour);
                        }
                        else
                        {
                            timeStringP3=String.valueOf(hour);
                        }

                        if(minute<10)
                        {
                            timeStringP4="0"+String.valueOf(minute);
                        }
                        else
                        {
                            timeStringP4=String.valueOf(minute);
                        }

                        Log.d("Timerhour", String.valueOf(hour));
                        Log.d("Timerminute", String.valueOf(minute));


                        timeStringF = timeStringP1 + timeStringP2 + timeStringP3 + timeStringP4;
                        setTimeStringP5();
                        timeStringFF = timeStringF + timeStringP5;
                        setTimeStringP6();
                        timeStringFinal = timerJ+ timeStringFF + timeStringP6;

                        if (timeStringP1.length() > 0 && timeStringP2.length() > 0 && timeStringP3.length() > 0 && timeStringP4.length() > 0 && timeStringP5.length() > 0 && timeStringP6.length() > 0) {
                            sendMessage(timeStringFinal, 0, 20);
                            Toast.makeText(MainActivity.this,
                                    "Successfully Time Set", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Please Fill All Data First", Toast.LENGTH_LONG).show();
                        }








                }   }else{
                            Toast.makeText(MainActivity.this,
                                    "Please Select Devices", Toast.LENGTH_LONG).show();

                        }

                    }else{Toast.makeText(MainActivity.this,
                                "Please Enter Hour And Minute", Toast.LENGTH_LONG).show();



                }}


                else
                {
                    Toast.makeText(MainActivity.this,
                            "Please Fill All Data First", Toast.LENGTH_LONG).show();
                }
            }
        }


        else{
            Toast.makeText(MainActivity.this,
                    "Not Connected", Toast.LENGTH_LONG).show();
        }


    }



}




