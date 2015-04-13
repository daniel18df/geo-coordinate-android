package com.example.mapexample;



import java.util.ArrayList;

import com.example.mapexample.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.splunk.mint.Mint;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends  FragmentActivity implements 
GooglePlayServicesClient.ConnectionCallbacks, 
GooglePlayServicesClient.OnConnectionFailedListener {
	
	private String[] titulos;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Item_objct> NavItms;
    private TypedArray NavIcons;	
    NavigationAdapter NavAdapter;  
    
    @SuppressWarnings("deprecation")
	private ActionBarDrawerToggle mDrawerToggle;
	
	
	private GoogleMap googleMap;
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /** LIBRERIA MINT PARA SEGUIR BUGS*/
        Mint.initAndStartSession(MainActivity.this, "07e69563");
        
        
        
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6F00")));
        actionBar.setTitle("     Mapa");

        actionBar.setDisplayHomeAsUpEnabled(true);
        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                 //la actividad
                NavDrawerLayout,         //el drawerLayout que desplegar�
                R.drawable.ic_drawer, //el icono que mostraremos
                R.string.app_name,  //descripci�n al abrir
                R.string.app_name  //descripci�n al cerrar
                ) {     };

		MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		googleMap = mapFragment.getMap();
		googleMap.setMyLocationEnabled(true);
		
		
		//Drawer Layout
		
		//NavDrawerLayout = (DrawerLayout) findViewById(R.id.);

		//Lista
        NavList = (ListView) findViewById(R.id.lista);
        //Declaramos el header el caul sera el layout de header.xml
        View header = getLayoutInflater().inflate(R.layout.header, null);
        //Establecemos header
        NavList.addHeaderView(header);
		//Tomamos listado  de imgs desde drawable
        NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);			
		//Tomamos listado  de titulos desde el string-array de los recursos @string/nav_options
        titulos = getResources().getStringArray(R.array.nav_options);
        //Listado de titulos de barra de navegacion
        NavItms = new ArrayList<Item_objct>();
        //Agregamos objetos Item_objct al array
        //Perfil	      
        NavItms.add(new Item_objct(titulos[0], NavIcons.getResourceId(0, -1)));
        //Favoritos
        NavItms.add(new Item_objct(titulos[1], NavIcons.getResourceId(1, -1)));
        //Eventos
        NavItms.add(new Item_objct(titulos[2], NavIcons.getResourceId(2, -1)));
        //Lugares
        NavItms.add(new Item_objct(titulos[3], NavIcons.getResourceId(3, -1)));
        //Etiquetas
        NavItms.add(new Item_objct(titulos[4], NavIcons.getResourceId(4, -1)));
        //Configuracion
        NavItms.add(new Item_objct(titulos[5], NavIcons.getResourceId(5, -1)));
        //Share
        NavItms.add(new Item_objct(titulos[6], NavIcons.getResourceId(6, -1)));	      
        //Declaramos y seteamos nuestrp adaptador al cual le pasamos el array con los titulos	       
        NavAdapter= new NavigationAdapter(this,NavItms);
        NavList.setAdapter(NavAdapter);	
        //Siempre vamos a mostrar el mismo titulo
        
        
		NavList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				if(titulos[0].equalsIgnoreCase(titulos[arg2-1]) == true){
					Toast toast1 = Toast.makeText(getApplicationContext(),"1 "+titulos[0], Toast.LENGTH_SHORT);
					toast1.show();  
				}
				if(titulos[1].equalsIgnoreCase(titulos[arg2-1]) == true){
					Toast toast1 = Toast.makeText(getApplicationContext(),"2 "+titulos[1], Toast.LENGTH_SHORT);
					toast1.show();  
				}
				if(titulos[2].equalsIgnoreCase(titulos[arg2-1]) == true){
					Toast toast1 = Toast.makeText(getApplicationContext(),"3 "+titulos[2], Toast.LENGTH_SHORT);
					toast1.show();  
				}
				if(titulos[3].equalsIgnoreCase(titulos[arg2-1]) == true){
					Toast toast1 = Toast.makeText(getApplicationContext(),"4 "+titulos[3], Toast.LENGTH_SHORT);
					toast1.show();  
				}
				//Se cierra el men�
				NavDrawerLayout.closeDrawers();
			}
		});
       	              
		
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected1(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub	
	}
	
    //Que el bot�n de desplegar siempre este sincronizado
	@SuppressWarnings("deprecation")
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	//Igual con la configuraci�n
	@SuppressWarnings("deprecation")
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	//Activamos el click paradesplegar
	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}//main Activity
