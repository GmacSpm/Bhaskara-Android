package com.GmacSpm.Bhaskara;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;

import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Toast;
import android.widget.TextView;
import android.widget.EditText;
import android.util.Log;

import java.io.File;
// import com.google.android.gms.ads.AdRequest;
// import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Bhaskara";
    private EditText termo_a, termo_b, termo_c;

    private Animation anim_bounce;
    private TextView calculo_raiz;
    private TextView denominador, denominador_dois, denominador_quatro, denominador_quatro_quatro, denominador_tres, denominador_tres_tres;
    private Animation deslize;
    private TextView numerador, numerador_dois, numerador_quatro, numerador_quatro_quatro, numerador_tres, numerador_tres_tres, resultado_x1, resultado_x2;
    private TextView saida;

    private void getWidgets() {
        deslize = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.debaixo);
        calculo_raiz = (TextView) findViewById(R.id.saida);
        anim_bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        termo_a = (EditText) findViewById(R.id.a);
        termo_b = (EditText) findViewById(R.id.b);
        termo_c = (EditText) findViewById(R.id.c);

        numerador = (TextView) findViewById(R.id.numerador);
        denominador = (TextView) findViewById(R.id.denominador);
        numerador_dois = (TextView) findViewById(R.id.numerador_dois);

        saida = (TextView) findViewById(R.id.saida);
        denominador_dois = (TextView) findViewById(R.id.denominador_dois);
        numerador_tres = (TextView) findViewById(R.id.numerador_tres);
        denominador_tres = (TextView) findViewById(R.id.denominador_tres);
        numerador_quatro = (TextView) findViewById(R.id.numerador_quatro);
        denominador_quatro = (TextView) findViewById(R.id.denominador_quatro);
        numerador_tres_tres = (TextView) findViewById(R.id.numerador_tres_tres);
        denominador_tres_tres = (TextView) findViewById(R.id.denominador_tres_tres);
        numerador_quatro_quatro = (TextView) findViewById(R.id.numerador_quatro_quatro);
        denominador_quatro_quatro = (TextView) findViewById(R.id.denominador_quatro_quatro);
        resultado_x1 = (TextView) findViewById(R.id.resultado_x1);
        resultado_x2 = (TextView) findViewById(R.id.resultado_x2);
    }

    private void startAnimations() {
        numerador.startAnimation(anim_bounce);
        denominador.startAnimation(anim_bounce);
        numerador_dois.startAnimation(anim_bounce);
        denominador_dois.startAnimation(anim_bounce);
        numerador_tres.startAnimation(anim_bounce);
        denominador_tres.startAnimation(anim_bounce);
        numerador_quatro.startAnimation(anim_bounce);
        denominador_quatro.startAnimation(anim_bounce);
        numerador_tres_tres.startAnimation(anim_bounce);
        denominador_tres_tres.startAnimation(anim_bounce);
        numerador_quatro_quatro.startAnimation(anim_bounce);
        denominador_quatro_quatro.startAnimation(anim_bounce);
        resultado_x1.startAnimation(anim_bounce);
        resultado_x2.startAnimation(anim_bounce);
        calculo_raiz.startAnimation(anim_bounce);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);
        
        getWidgets();
        //((AdView) findViewById(R.id.adView)).loadAd(new AdRequest.Builder().build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
        case R.id.ajuda:
            try {
                startActivity(new Intent(this, Class.forName("com.GmacSpm.Bhaskara.AjudaActivity")));
                return true;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        default:
            return super.onOptionsItemSelected(menuItem);
        }
    }

    public void ajuda(View view) {
        try {
            startActivity(new Intent(this, Class.forName("com.GmacSpm.Bhaskara.AjudaActivity")));
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void enviar(View view) {
        try {
            String str = getApplicationContext().getApplicationInfo().sourceDir;
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str)));
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Erro: " + e.toString());
        }
    }

    private int getEditTextValue(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            editText.setText("0");
            return 0;
        }
        return new Integer(Integer.parseInt(editText.getText().toString()));
    }

    public void calculoDelta(View view) {
        try {
            int termoA = getEditTextValue(termo_a);
            int termoB = getEditTextValue(termo_b);
            int termoC = getEditTextValue(termo_c);

            startAnimations();

            //B^2 - 4 x A x C
            String aboveLine = -termoB + "±√" + termoB * termoB + "- 4 x " + termoA + " x " + termoC;
            int insideSqrt = (termoB * termoB) - ((4 * termoA) * termoC);
            int belowLine = 2 * termoA;
            int sqrt = (int) Math.sqrt((double) insideSqrt);
            numerador.setText(aboveLine);
            denominador.setText(String.valueOf(belowLine));

            //Se o valor dentro da raiz for menor que zero, não existe raiz real.
            if (insideSqrt < 0) {
                limpa();
                saida.setText("A equação não tem raiz real");
            }
            //Se a raiz quadrada for maior que zero e igual a mesma multiplicada, serão duas raizes a calcular.
            else if (insideSqrt > 0 && insideSqrt == sqrt * sqrt) {
                saida.setText("Calcular duas raízes");
                int sqrt2 = (-termoB) + ((int) Math.sqrt((double) insideSqrt));
                int insideSqrt3 = 2 * termoA;

                double d = ((double) sqrt2) / ((double) insideSqrt3);
                int i = sqrt2 / insideSqrt3;
                int sqrt3 = (-termoB) - ((int) Math.sqrt((double) insideSqrt));
                double insideSqrt4 = ((double) sqrt3) / ((double) (2 * termoA));
                int insideSqrt5 = sqrt3 / (2 * termoA);

                if (d == ((double) i) && insideSqrt4 == ((double) insideSqrt5)) {
                    numerador_dois.setText("- " + termoB + "±" + " √" + insideSqrt);
                    denominador_dois.setText(String.valueOf(belowLine));
                    numerador_tres.setText(-termoB + "+" + Math.sqrt(insideSqrt));
                    numerador_tres_tres.setText(String.valueOf(sqrt2));
                    denominador_tres.setText(String.valueOf(belowLine));
                    denominador_tres_tres.setText(String.valueOf(belowLine));
                    resultado_x1.setText("X¹= " + i);
                    numerador_quatro.setText(-termoB + "-" + Math.sqrt(insideSqrt));
                    numerador_quatro_quatro.setText(String.valueOf(sqrt3));
                    denominador_quatro.setText(String.valueOf(belowLine));
                    denominador_quatro_quatro.setText(String.valueOf(belowLine));
                    resultado_x2.setText("X²= " + insideSqrt5);
                } else {
                    numerador_dois.setText("- " + termoB + "±" + " √" + insideSqrt);
                    denominador_dois.setText(String.valueOf(belowLine));
                    numerador_tres.setText(-termoB + "+" + (int) Math.sqrt(insideSqrt));
                    numerador_tres_tres.setText(String.valueOf(sqrt2));
                    denominador_tres.setText(String.valueOf(belowLine));
                    denominador_tres_tres.setText(String.valueOf(belowLine));
                    resultado_x1.setText("X¹= " + d);
                    numerador_quatro.setText(-termoB + "-" + Math.sqrt(insideSqrt));
                    numerador_quatro_quatro.setText(String.valueOf(sqrt3));
                    denominador_quatro.setText(String.valueOf(belowLine));
                    denominador_quatro_quatro.setText(String.valueOf(belowLine));
                    resultado_x2.setText("X²= " + insideSqrt4);
                }
            } else if (insideSqrt > 0) {
                saida.setText("Calcular duas raízes");
                double sqrt4 = (((double) (-termoB)) + Math.sqrt((double) insideSqrt)) / ((double) (2 * termoA));
                double sqrt5 = (((double) (-termoB)) - Math.sqrt((double) insideSqrt)) / ((double) (2 * termoA));
                String valueOf2 = String.valueOf(insideSqrt);
                numerador_dois.setText("- " + termoB + "±" + " √" + valueOf2);
                denominador_dois.setText(String.valueOf(belowLine));
                numerador_tres.setText("- " + termoB + "-" + " √" + valueOf2);
                numerador_tres_tres.setText("- " + termoB + "-" + " √" + valueOf2);
                denominador_tres.setText(String.valueOf(belowLine));
                denominador_tres_tres.setText(String.valueOf(belowLine));
                resultado_x1.setText("Agora é com você 👍");
                numerador_quatro.setText("- " + termoB + "+" + " √" + valueOf2);
                numerador_quatro_quatro.setText("- " + termoB + "+" + " √" + valueOf2);
                denominador_quatro.setText(String.valueOf(belowLine));
                denominador_quatro_quatro.setText(String.valueOf(belowLine));
                resultado_x2.setText("Também é com você ☺");
            } else {
                saida.setText("Calculo uma raíz apenas");
                int sqrt6 = -termoB + (int) Math.sqrt(insideSqrt);
                int insideSqrt6 = 2 * termoA;
                int i2 = sqrt6 / insideSqrt6;
                numerador_dois.setText("- " + termoB + "±" + " √" + (insideSqrt));
                denominador_dois.setText(String.valueOf(belowLine));
                numerador_tres.setText(-termoB + "+" + (int) Math.sqrt((double) insideSqrt));
                numerador_tres_tres.setText(String.valueOf(sqrt6));
                denominador_tres_tres.setText(String.valueOf(insideSqrt6));
                denominador_tres.setText(String.valueOf(belowLine));
                resultado_x1.setText("X¹= " + i2);
                numerador_quatro.setText("");
                denominador_quatro.setText("");
                numerador_quatro_quatro.setText("");
                denominador_quatro_quatro.setText("");
                resultado_x2.setText("");
            }
        } catch (Exception e) {
            View inflate = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastRelativeLayout1));
            Toast makeText = Toast.makeText(this, "Toast:Gravity.BOTTON", 0);
            makeText.setGravity(80, 0, 0);
            makeText.setView(inflate);
            makeText.show();
        }
    }

    private void limpa() {
        termo_a.setText("");
        termo_b.setText("");
        termo_c.setText("");
        numerador.setText("");
        denominador.setText("");
        numerador_dois.setText("");
        denominador_dois.setText("");
        numerador_tres.setText("");
        denominador_tres.setText("");
        numerador_quatro.setText("");
        denominador_quatro.setText("");
        numerador_tres_tres.setText("");
        denominador_tres_tres.setText("");
        numerador_quatro_quatro.setText("");
        denominador_quatro_quatro.setText("");
        resultado_x1.setText("");
        resultado_x2.setText("");
    }
}
