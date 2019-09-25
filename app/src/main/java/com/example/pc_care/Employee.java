package com.example.pc_care;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.List;

import Database_company.DatabaseHelper;

import static java.lang.Boolean.FALSE;

public class Employee extends AppCompatActivity {

    private EditText txt_name;

    private EditText txt_age;

    private EditText txt_phone;

    private EditText txt_email;



    private EditText txt_salary;

    String nameUI, ageto, phoneto, emailUI, ageUI, salaryto;
    ImageView mImageView;
    Button mbtnAdd, mbtnList;
    private Validator validator;
    Boolean a;

    final int REQUEST_CODE_GALLERY = 999;
    public static DatabaseHelper mDatabaseHelper;
    private Object TextUtils;
   // private Object TextUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);


        txt_name = findViewById(R.id.editName);
        txt_age = findViewById(R.id.editAge);
        txt_phone = findViewById(R.id.editPhone);
        txt_email = findViewById(R.id.editEmail);
        txt_salary = findViewById(R.id.editSalary);
        mbtnList = findViewById(R.id.btnList);

        mImageView = findViewById(R.id.imageView);

        initView();

        txt_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {

                String empname = txt_name.getText().toString();
                boolean  name =  validatename(empname);

            }
        });


        txt_age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String empage = txt_age.getText().toString();
                boolean device =   validate_age(empage);

            }});
//
        txt_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String phone = txt_phone.getText().toString();
                boolean  validaphone =   PhoneNumber_Validate(phone);

            }
        });


        txt_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String cusemail = txt_email.getText().toString();
                boolean numbers =  validate_email(cusemail);
//
            }
        });


    }











    private void initView() {

        mDatabaseHelper = new DatabaseHelper(this);
        // mbtnAdd.setOnClickListener(new View.OnClickListener() {
        // @Override
       //public void onClick(View view) {
               // buttonSave_onClick(view);
           // }
       // });
   // }

   // private void buttonSave_onClick(View view) {
        //validator.validate();
        //String username = txt_name.getText().toString();
        //if (username.equalsIgnoreCase("pmk")) {
        //}
    //}

        /*textInputName = findViewById(R.id.text_input_name);
        textInputAge = findViewById(R.id.text_input_age);
        textInputPhone = findViewById(R.id.text_input_phone);
        textInputEmail = findViewById(R.id.text_input_email);
        textInputSalary = findViewById(R.id.text_input_salary);*/


        //select image by on imageView click
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //read external storgae permission to select image from gallery
                //Runtime permission
                ActivityCompat.requestPermissions(
                        Employee.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });


        mbtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start Record the list
                startActivity(new Intent(Employee.this, EmployeeList.class));
            }
        });


    }

    public static byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;





    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(this, "Don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && requestCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON) //enable image guidline
                    .setAspectRatio(1, 1) //image will be square
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

        }


    }


   public void AddInfo(View view) {

       String empname = txt_name.getText().toString();
       boolean  name =  validatename(empname);

       String empage = txt_age.getText().toString();
       boolean validaage =   validate_age(empage);

       String phone = txt_phone.getText().toString();
       boolean  validaphone =   PhoneNumber_Validate(phone);

       String cusemail = txt_email.getText().toString();
       boolean validemail =  validate_email(cusemail);



       if(name == FALSE || validaage ==  FALSE || validaphone ==  FALSE ||  validemail== FALSE) {

           Drawable customErrorDrawable = getResources().getDrawable(R.drawable.error);
           customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());

           Toast.makeText(getApplicationContext(), "INSERTED ERROR", Toast.LENGTH_LONG).show();
       }

     else {


           nameUI = txt_name.getText().toString();
           ageto = txt_age.getText().toString();
           int ageUI = Integer.parseInt(ageto);
           phoneto = txt_phone.getText().toString();
           int phoneUI = Integer.parseInt(phoneto);
           emailUI = txt_email.getText().toString();
           salaryto = txt_salary.getText().toString();
           double salaryUI = Double.parseDouble(salaryto);
           //validator.validate();
           //String username = txt_name.getText().toString();
           //if (username.equalsIgnoreCase("pmk")) {
             //  txt_name.setError(getText(R.string.username_already_exists));
           //}
           boolean result = mDatabaseHelper.addUser(nameUI, ageUI, phoneUI, emailUI, salaryUI);
           if (result == true) {

               Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();


           } else if (result == false) {


               Toast.makeText(getApplicationContext(), "Adding Error", Toast.LENGTH_LONG).show();

           }
       }
    }


    private boolean validatename(String name)
    {


        Drawable customErrorDrawable = getResources().getDrawable(R.drawable.error);
        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());

        if(name.isEmpty())
        {   //txt_cusname.requestFocus();
            txt_name.setError("Field can't be empty",customErrorDrawable);
            a = false;
        }
        else if(name.length()>15)
        {    //txt_cusname.requestFocus();
            txt_name.setError("Username too long",customErrorDrawable);
            a = false;
        }
        else if(!name.matches("[a-zA-Z ]+"))
        {
            //txt_cusname.requestFocus();
            txt_name.setError("Enter Only Alphabeticaa character",customErrorDrawable);
            a = false;
        }
        else if(!name.isEmpty() && name.length()<15 && name.matches("[a-zA-Z ]+"))
        {
            a = true;
        }

        return  a;
    }





    private Boolean  PhoneNumber_Validate(String number)
    {

        Drawable customErrorDrawable = getResources().getDrawable(R.drawable.error);
        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());

        if(number.isEmpty())
        {   //txt_issues.requestFocus();
            txt_phone.setError("Field can't be empty",customErrorDrawable);
            a = false;
        }

        if( (number.length()==10) && android.util.Patterns.PHONE.matcher(number).matches())
        {
            a = true;
        }

        else if(number.length()!=10)
        {
            txt_phone.setError("Enter 10 numbers Only",customErrorDrawable);
            a = false;
        }

        // txt_phoneno.setError("Enter Only Numbers Only",customErrorDrawable);

        return  a;
    }

    private boolean   validate_age(String age)
    {

        //String regexstr = "^[0-9]*$";
        Drawable customErrorDrawable = getResources().getDrawable(R.drawable.error);
        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());


        if(age.isEmpty())
        {   //txt_issues.requestFocus();
            txt_age.setError("Field can't be empty",customErrorDrawable);
            a = false;
        }


       //if( Integer.parseInt(age)<= 18)
        //{
          // txt_age.setError("Age should be greater than 18",customErrorDrawable);
            //a = false;
        //}

        //txt_totamount.setError("Enter Only Numbers Only",customErrorDrawable);
        else if(!age.isEmpty())
        {
            a = true;
        }

        return  a;
    }

    private boolean   validate_email(String email) {


        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        Drawable customErrorDrawable = getResources().getDrawable(R.drawable.error);
        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());

        if (email.isEmpty()) {
            txt_email.setError("Enter email address",customErrorDrawable);
            //Toast.makeText(getApplicationContext(), "enter email address", Toast.LENGTH_SHORT).show();
            a = false;
        } else {
            if (email.matches(emailPattern)) {
                txt_email.setError("valid email address",customErrorDrawable);
                //Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                a = true;
            } else {
                txt_email.setError("Invalid email address",customErrorDrawable);
                //Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
               a = false;
            }
        }

     return a;
    }





}

