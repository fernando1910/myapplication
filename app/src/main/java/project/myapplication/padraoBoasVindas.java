package project.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

public class padraoBoasVindas extends Activity {

    clsConfiguracoes objConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padrao_boas_vindas);

        clsConfiguracoes objConfig = null;
        ConfiguracoesDAO config_dao = new ConfiguracoesDAO(this.getApplicationContext());
        objConfig = config_dao.Carregar();

        switch (objConfig.getStatusPerfil())
        {
            case 0:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,padraoCadTelefone.class));
                break;
            case 3:
                startActivity(new Intent(this,padraoMenu.class));
                break;
        }

    }

    public void onClick_Avancar(View v)
    {

            ConfiguracoesDAO config_dao = new ConfiguracoesDAO(this.getApplicationContext());
            objConfig = config_dao.Carregar();
            objConfig.setStatusPerfil(2);

            config_dao.Atualizar(objConfig);
            startActivity(new Intent(this, padraoCadTelefone.class));
    }
}
