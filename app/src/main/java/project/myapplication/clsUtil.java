package project.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
class clsUtil {

    public String RetornaDataHoraMinuto()
    {
        String DataHora;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        DataHora=  dateFormat.format(calendar.getTime());
        return DataHora;
    }

    public Date formataData(String date)
    {
        Date dataConvertida = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
        try{
            dataConvertida = simpleDateFormat.parse(date);
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        return  dataConvertida;
    }


    public Drawable retornarIcone(Drawable drawable, Resources resources)
    {

        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        drawable = new BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap,100,100,true));
        return drawable;

    }

    public String formatarDataBanco(Date data)
    {
        String dataFinal;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dataFinal = dateFormat.format(data);
        return dataFinal;
    }

    public String formatarDataTela(Date data)
    {
        String dataFinal;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dataFinal = dateFormat.format(data);
        return dataFinal;
    }


    public String formatarStringDataBanco(String data)
    {
        String dataFinal = null;
        data = data.replace("/","-");
        String[] dataTemp = data.split("//-");

        int i = 0;

        while (i> dataTemp.length)
        {
            dataFinal = dataFinal + dataTemp[i];
            i++;
        }

        return   dataFinal;
    }

    public String enviarServidor(final String url,final String data, final String comando) throws InterruptedException {
        final String[] resposta = new String[1];
        Thread thread = new Thread(){
            public void run(){
                resposta[0] =  project.myapplication.HttpConnection.getSetDataWeb(url,comando ,data);

            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resposta[0];
    }

    public clsUtil() {
    }

}
