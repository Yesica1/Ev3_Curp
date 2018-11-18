package com.example.vostro.ev3_curp.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.vostro.ev3_curp.R;

public class Usuario implements  Parcelable {
    //VARIABLES
    String names;
    String firstlastname;
    String secondlastname;
    String gender;
    Integer month;
    Integer day;
    Integer year;
    String federal_entity;
    String url;

    //CONSTRUCTORES
    public Usuario()
    {

    }

    public Usuario(String names, String firstlastname, String secondlastname, String gender, Integer month, Integer day, Integer year, String federal_entity, String url ) //
    {
        this.names = names;
        this.firstlastname = firstlastname;
        this.secondlastname = secondlastname;
        this.gender = gender;
        this.month = month;
        this.day = day;
        this.year = year;
        this.federal_entity = federal_entity;
        this.url = url;
    }

    //GET Y SETS
    public String getNames() {
        return names;
    }

    public void setName(String names) {
        this.names = names;
    }

    public String getFirstlastname() {
        return firstlastname;
    }

    public void setFirstlastname(String firstlastname) {
        this.firstlastname = firstlastname;
    }

    public String getSecondlastname() {
        return secondlastname;
    }


    public void setSecondlastname(String secondlastname) {
        this.secondlastname = secondlastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFederal_entity() {
        return federal_entity;
    }

    public void setFederal_entity(String federal_entity) {
        this.federal_entity = federal_entity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //PARCELABLE
    protected Usuario(Parcel in) {
        names = in.readString();
        firstlastname = in.readString();
        secondlastname = in.readString();
        gender = in.readString();
        month = in.readInt();
        day = in.readInt();
        year = in.readInt();
        federal_entity = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(names);
        dest.writeString(firstlastname);
        dest.writeString(secondlastname);
        dest.writeString(gender);
        dest.writeInt(month);
        dest.writeInt(day);
        dest.writeInt(year);
        dest.writeString(federal_entity);
        dest.writeString(url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };




    /**
     * Metodo a utilizar para poder calcular el Curp del usuario al ingresar sus datos. Este se llamara
     * desde el ListaUsuarioRecycler para poderlo mostrar en un TextView.
     * Este método contiene todos los datos necesarios para poder generar el CURP correcto, segun se requiera.
     */
    public String realizacionCurp() {
        //Convertir los datos a mayusculas
        firstlastname = firstlastname.toUpperCase();
        secondlastname = secondlastname.toUpperCase();
        names = names.toUpperCase();

        char parte1 = firstlastname.charAt(0); //primera letra del apellido paterno
        char parte2 = 0;
        //Sacar la primera vocal
        for (int i = 1; i < firstlastname.length(); i++) {
            char vocales = firstlastname.charAt(i);
            if (vocales == 'A' || vocales == 'E' || vocales == 'I' || vocales == 'O' || vocales == 'U') {
                parte2 = vocales;
                break;
            }
        }
        char parte3 = secondlastname.charAt(0); //primera letra del apellido materno
        char parte4 = names.charAt(0); //primera letra del nombre

        String year = String.valueOf(getYear()); //convertir a string
        char parte5 = year.charAt(2); //tercer numero del año
        char parte6 = year.charAt(3); //cuarto numero del año

        String month = String.valueOf(getMonth()); //convertirlo a string
        char parte7;//primer numero del mes
        char parte8;//segundo numero del mes
        //ver si es menor a 10 para poderle agregar un 0 si se necesita
        if (getMonth() < 10) {
            parte7 = '0';
            parte8 = month.charAt(0);
        } else {
            parte7 = month.charAt(0);
            parte8 = month.charAt(1);
        }

        String day = String.valueOf(getDay()); //convertirlo a string
        char parte9; //primer numero del dia
        char parte10; // segundo numero del dia

        //ver si es menor a 10 para agregar un 0 si se necesita
        if (getDay() < 10) {
            parte9 = 0;
            parte10 = day.charAt(0);
        } else {
            parte9 = day.charAt(0);
            parte10 = day.charAt(1);
        }


        char parte11gen; // variable del genero
        //Si el contenido de la variable genero es igual a Feminine o Femenino mostrará M y sino mostrara H
        if (gender.contentEquals("Feminine" ) ||gender.contentEquals("Femenino"))
        {
            parte11gen = 'M';
        }else
        {
            parte11gen = 'H';
        }

        char parte12 = 0; //primera parte de la abreviación del estado
        char parte13 = 0; //segunda parte de la abreviaion del estado

        //este metodo asigna las letras de la entidad federativa
        switch(federal_entity){
            case "Aguascalientes":
                parte12='A';
                parte13='S';
                break;

            case "Baja California":
                parte12='B';
                parte13='C';
                break;

            case "Baja California Sur":
                parte12='B';
                parte13='S';

                break;
            case "Campeche":
                parte12='C';
                parte13='C';
                break;
            case "Chiapas":
                parte12='C';
                parte13='S';
                break;

            case "Cuidad de México":
                parte12='D';
                parte13='F';
                break;

            case "Durango":
                parte12='D';
                parte13='G';
                break;

            case "Guanajuato":
                parte12='G';
                parte13='T';
                break;

            case "Guerrero":
                parte12='G';
                parte13='R';
                break;

            case "Hidalgo":
                parte12='H';
                parte13='G';
                break;

            case "Jalisco":
                parte12='J';
                parte13='C';
                break;

            case "Estado de México":
                parte12='E';
                parte13='M';
                break;
            case "Michoacan de Ocampo":
                parte12='M';
                parte13='N';
                break;

            case "Morelos":
                parte12='M';
                parte13='S';
                break;

            case "Nayarit":
                parte12='N';
                parte13='T';
                break;

            case "Nuevo León":
                parte12='N';
                parte13='L';
                break;

            case "Oaxaca":
                parte12='O';
                parte13='C';
                break;

            case "Puebla":
                parte12='P';
                parte13='L';
                break;

            case "Querétaro":
                parte12='Q';
                parte13='T';
                break;

            case "Quintana Roo":
                parte12='Q';
                parte13='R';
                break;

            case "San Luis Potosí":
                parte12='S';
                parte13='P';
                break;

            case "Sinaloa":
                parte12='S';
                parte13='L';
                break;

            case "Sonora":
                parte12='S';
                parte13='R';
                break;

            case "Tabasco":
                parte12='T';
                parte13='C';
                break;

            case "Tamaulipas":
                parte12='T';
                parte13='S';
                break;
            case "Tlaxcala":
                parte12='T';
                parte13='L';
                break;

            case "Veracruz":
                parte12='V';
                parte13='Z';
                break;

            case "Yucatán":
                parte12='Y';
                parte13='N';
                break;

            case "Zacatecas":
                parte12='Z';
                parte13='S';
                break;
        }


        char parte14 = 0; //variable que almacenará la  primera consonante del apellido paterno
        for (int i = 1; i < firstlastname.length(); i++) {
            char primerconsonantePat = firstlastname.charAt(i);
            //Este tiene que ser diferente a las vocales para que se refiera a las consonantes
            if (primerconsonantePat != 'A' && primerconsonantePat != 'E'&& primerconsonantePat != 'I' && primerconsonantePat != 'O' && primerconsonantePat != 'U') {
                parte14 = primerconsonantePat;
                break;
            }
        }
        char parte15 = 0; //variable que almacenará la primera consonante del apellido materno
        for (int i = 1; i < secondlastname.length(); i++) {
            char primerconsonanteMat = secondlastname.charAt(i);
            //Este tiene que ser diferente a las vocales para que se refiera a las consonantes
            if (primerconsonanteMat != 'A' &&  primerconsonanteMat != 'E' && primerconsonanteMat != 'I' && primerconsonanteMat != 'O' && primerconsonanteMat != 'U') {
                parte15 = primerconsonanteMat;
                break;
            }
        }

        char parte16 = 0; //variable que almacenará la primera consonante del nombre
        for (int i = 1; i < names.length(); i++) {
            char primerconsonanteNomb = names.charAt(i);
            //Este tiene que ser diferente a las vocales para que se refiera a las consonantes
            if (primerconsonanteNomb != 'A' && primerconsonanteNomb != 'E' && primerconsonanteNomb != 'I' && primerconsonanteNomb != 'O' && primerconsonanteNomb != 'U') {
                parte16 = primerconsonanteNomb;
                break;
            }
        }

        return (parte1 + "" + parte2 + "" + parte3 + "" + parte4 + "" + parte5 + "" + parte6 + "" + parte7 + "" + parte8 + "" + parte9 +
                "" + parte10 + "" +parte11gen+ ""+ parte12+ "" +parte13+""+parte14+""+parte15+""+parte16);

    }


}
