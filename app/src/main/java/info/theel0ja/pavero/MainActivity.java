package info.theel0ja.pavero;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.text.TextUtils;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void calculateTaxesBensiini(View view) {
        // EditTextit
        EditText hinta = (EditText) findViewById(R.id.editText);
        String verollinenTxt = hinta.getText().toString();

        if (TextUtils.isEmpty(verollinenTxt)) {
            // nothing
            // some modal etc?
        }
        else {
            double verollinen = Double.parseDouble(hinta.getText().toString());
            // TextViewit
            TextView TextView1 = (TextView) findViewById(R.id.printa);
            TextView TextView2 = (TextView) findViewById(R.id.printb);
            TextView TextView3 = (TextView) findViewById(R.id.printc);
            TextView TextView4 = (TextView) findViewById(R.id.printd);
            TextView TextView5 = (TextView) findViewById(R.id.printe);
            TextView TextView6 = (TextView) findViewById(R.id.printf);

            // Veroprosentit
            double alv = 0.24;

            // Verot euroina per litra
            double energiasisaltovero = 51.20 / 100;
            double hiilidioksidivero = 16.25 / 100;

            // Veron kaltaiset per litra
            double huoltovarmuusmaksu = 0.68 / 100;

            // Laske verot
            double verot;
            // ALV
            verot = verollinen * alv;
            // Kiinteät verot
            verot = verot + energiasisaltovero;
            verot = verot + hiilidioksidivero;
            verot = verot + huoltovarmuusmaksu;

            // Muuttujat
            double veroton = verollinen - verot;
            double veroProsentti = verot / verollinen * 100;
            double alvEuro = verollinen * alv;

            // Aseta ne TextVieweihin
            TextView1.setText(getString(R.string.energiasisaltovero) + ": " + energiasisaltovero + "€");
            TextView2.setText(getString(R.string.hiilidioksidivero) + ": " + hiilidioksidivero + "€");
            //TextView3.setText("Huoltovarmuusmaksu: " + huoltovarmuusmaksu + "€"); // round it!
            TextView3.setText(getString(R.string.huoltovarmuusmaksu) + ": 0.0068€");
            //TextView7.setText(getString(R.string.vat) + ":" + alvEuro + "€");
            TextView4.setText(getString(R.string.totalTaxes) + ": " + verot + "€");
            TextView5.setText(getString(R.string.veroton) + ": " + veroton + "€");
            TextView6.setText(getString(R.string.verollinen) + ": " + veroProsentti + "%");
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
