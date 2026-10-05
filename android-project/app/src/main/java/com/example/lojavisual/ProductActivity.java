package com.example.lojavisual;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ProductActivity extends Activity {
    private int qty = 1;
    private int dp(float v){ return Math.round(v*getResources().getDisplayMetrics().density); }
    private TextView text(String s,float z,int c,boolean b){ TextView v=new TextView(this); v.setText(s); v.setTextSize(z); v.setTextColor(c); if(b)v.setTypeface(null,android.graphics.Typeface.BOLD); return v; }
    private GradientDrawable bg(int c,float r){ GradientDrawable d=new GradientDrawable(); d.setColor(c); d.setCornerRadius(dp(r)); return d; }

    @Override public void onCreate(Bundle b){
        super.onCreate(b);
        getWindow().setStatusBarColor(Color.WHITE); getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        String emoji=getIntent().getStringExtra("emoji"); String name=getIntent().getStringExtra("name"); String price=getIntent().getStringExtra("price"); String desc=getIntent().getStringExtra("description"); int color=getIntent().getIntExtra("color",Color.rgb(240,240,240));

        ScrollView scroll=new ScrollView(this); scroll.setBackgroundColor(Color.WHITE);
        LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(20),dp(16),dp(20),dp(28)); scroll.addView(root);

        TextView back=text("‹  Voltar",16,Color.rgb(70,70,76),true); back.setPadding(0,dp(6),0,dp(6)); back.setOnClickListener(v->finish()); root.addView(back);
        TextView image=text(emoji,78,Color.BLACK,false); image.setGravity(Gravity.CENTER); image.setBackground(bg(color,28)); LinearLayout.LayoutParams ilp=new LinearLayout.LayoutParams(-1,dp(280)); ilp.topMargin=dp(16); root.addView(image,ilp);

        TextView category=text("MAIS VENDIDO",12,Color.rgb(255,107,53),true); LinearLayout.LayoutParams clp=new LinearLayout.LayoutParams(-1,-2); clp.topMargin=dp(24); root.addView(category,clp);
        TextView title=text(name,28,Color.rgb(30,30,36),true); LinearLayout.LayoutParams tlp=new LinearLayout.LayoutParams(-1,-2); tlp.topMargin=dp(6); root.addView(title,tlp);
        TextView p=text(price,24,Color.rgb(255,107,53),true); LinearLayout.LayoutParams plp=new LinearLayout.LayoutParams(-1,-2); plp.topMargin=dp(8); root.addView(p,plp);
        TextView stars=text("★★★★★  4,9",15,Color.rgb(245,166,35),true); LinearLayout.LayoutParams slp=new LinearLayout.LayoutParams(-1,-2); slp.topMargin=dp(12); root.addView(stars,slp);
        TextView description=text(desc,15,Color.rgb(95,95,102),false); description.setLineSpacing(0,1.25f); LinearLayout.LayoutParams dlp=new LinearLayout.LayoutParams(-1,-2); dlp.topMargin=dp(18); root.addView(description,dlp);

        TextView qtdLabel=text("Quantidade",15,Color.rgb(45,45,52),true); LinearLayout.LayoutParams qllp=new LinearLayout.LayoutParams(-1,-2); qllp.topMargin=dp(26); root.addView(qtdLabel,qllp);
        LinearLayout qtyRow=new LinearLayout(this); qtyRow.setOrientation(LinearLayout.HORIZONTAL); qtyRow.setGravity(Gravity.CENTER_VERTICAL); LinearLayout.LayoutParams qrlp=new LinearLayout.LayoutParams(-1,dp(54)); qrlp.topMargin=dp(10); root.addView(qtyRow,qrlp);
        TextView minus=text("−",26,Color.rgb(50,50,55),true); minus.setGravity(Gravity.CENTER); minus.setBackground(bg(Color.rgb(245,245,247),14)); qtyRow.addView(minus,new LinearLayout.LayoutParams(dp(54),dp(54)));
        TextView value=text("1",18,Color.rgb(30,30,36),true); value.setGravity(Gravity.CENTER); qtyRow.addView(value,new LinearLayout.LayoutParams(dp(60),dp(54)));
        TextView plus=text("+",24,Color.WHITE,true); plus.setGravity(Gravity.CENTER); plus.setBackground(bg(Color.rgb(255,107,53),14)); qtyRow.addView(plus,new LinearLayout.LayoutParams(dp(54),dp(54)));
        minus.setOnClickListener(v->{ if(qty>1){qty--; value.setText(String.valueOf(qty));}}); plus.setOnClickListener(v->{qty++; value.setText(String.valueOf(qty));});

        Button buy=new Button(this); buy.setText("COMPRAR AGORA"); buy.setTextSize(15); buy.setTextColor(Color.WHITE); buy.setTypeface(null,android.graphics.Typeface.BOLD); buy.setAllCaps(false); buy.setBackground(bg(Color.rgb(255,107,53),18)); LinearLayout.LayoutParams blp=new LinearLayout.LayoutParams(-1,dp(60)); blp.topMargin=dp(30); root.addView(buy,blp);
        buy.setOnClickListener(v->{ Intent i=new Intent(this,PaymentActivity.class); i.putExtra("emoji",emoji); i.putExtra("name",name); i.putExtra("price",price); i.putExtra("qty",qty); i.putExtra("color",color); startActivity(i); });
        TextView safe=text("🔒  Compra segura • pagamento protegido",12,Color.rgb(120,120,126),false); safe.setGravity(Gravity.CENTER); LinearLayout.LayoutParams safelp=new LinearLayout.LayoutParams(-1,-2); safelp.topMargin=dp(12); root.addView(safe,safelp);
        setContentView(scroll);
    }
}
