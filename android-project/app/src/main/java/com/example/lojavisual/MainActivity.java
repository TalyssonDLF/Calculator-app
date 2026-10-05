package com.example.lojavisual;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int dp(float value) { return Math.round(value * getResources().getDisplayMetrics().density); }

    private TextView text(String value, float size, int color, boolean bold) {
        TextView v = new TextView(this);
        v.setText(value);
        v.setTextSize(size);
        v.setTextColor(color);
        if (bold) v.setTypeface(null, android.graphics.Typeface.BOLD);
        return v;
    }

    private GradientDrawable bg(int color, float radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(255,249,244));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.rgb(255,249,244));
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(18), dp(20), dp(32));
        scroll.addView(root);

        TextView brand = text("SHOPLY", 13, Color.rgb(255,107,53), true);
        root.addView(brand);

        TextView title = text("Encontre algo incrível", 28, Color.rgb(30,30,36), true);
        LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(-1,-2); titleLp.topMargin = dp(10);
        root.addView(title, titleLp);

        TextView subtitle = text("Produtos escolhidos para você", 15, Color.rgb(105,105,112), false);
        LinearLayout.LayoutParams subLp = new LinearLayout.LayoutParams(-1,-2); subLp.topMargin = dp(5);
        root.addView(subtitle, subLp);

        TextView search = text("🔎   Buscar produtos...", 15, Color.rgb(120,120,126), false);
        search.setGravity(Gravity.CENTER_VERTICAL);
        search.setPadding(dp(16),0,dp(16),0);
        search.setBackground(bg(Color.WHITE,18));
        LinearLayout.LayoutParams searchLp = new LinearLayout.LayoutParams(-1, dp(56)); searchLp.topMargin = dp(22);
        root.addView(search, searchLp);

        TextView section = text("Destaques", 20, Color.rgb(30,30,36), true);
        LinearLayout.LayoutParams secLp = new LinearLayout.LayoutParams(-1,-2); secLp.topMargin = dp(28); secLp.bottomMargin = dp(12);
        root.addView(section, secLp);

        addProduct(root, "🎧", "Headphone Pro", "R$ 149,90", "Som imersivo, confortável e perfeito para estudar, trabalhar ou jogar.", Color.rgb(235,241,255));
        addProduct(root, "⌚", "Smartwatch Fit", "R$ 199,90", "Acompanhe notificações e atividades físicas com um visual moderno.", Color.rgb(234,248,241));
        addProduct(root, "👟", "Tênis Urban", "R$ 299,90", "Conforto para o dia inteiro com estilo casual e acabamento premium.", Color.rgb(255,239,232));
        addProduct(root, "⌨️", "Teclado Compact", "R$ 189,90", "Teclado compacto e confortável para produtividade e jogos.", Color.rgb(244,238,255));

        setContentView(scroll);
    }

    private void addProduct(LinearLayout root, String emoji, String name, String price, String description, int tileColor) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp(12),dp(12),dp(14),dp(12));
        card.setBackground(bg(Color.WHITE,20));
        card.setElevation(dp(2));

        TextView icon = text(emoji, 34, Color.BLACK, false);
        icon.setGravity(Gravity.CENTER);
        icon.setBackground(bg(tileColor,16));
        card.addView(icon, new LinearLayout.LayoutParams(dp(86), dp(86)));

        LinearLayout info = new LinearLayout(this);
        info.setOrientation(LinearLayout.VERTICAL);
        info.setPadding(dp(16),0,0,0);
        LinearLayout.LayoutParams infoLp = new LinearLayout.LayoutParams(0,-2,1);
        TextView n = text(name,17,Color.rgb(35,35,40),true);
        TextView desc = text("Entrega rápida • estoque disponível",12,Color.rgb(120,120,126),false);
        TextView p = text(price,17,Color.rgb(255,107,53),true);
        info.addView(n);
        LinearLayout.LayoutParams dlp = new LinearLayout.LayoutParams(-1,-2); dlp.topMargin = dp(5); info.addView(desc, dlp);
        LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(-1,-2); plp.topMargin = dp(10); info.addView(p, plp);
        card.addView(info, infoLp);

        TextView arrow = text("›",30,Color.rgb(170,170,175),false);
        card.addView(arrow);

        card.setOnClickListener(v -> {
            Intent i = new Intent(this, ProductActivity.class);
            i.putExtra("emoji", emoji); i.putExtra("name", name); i.putExtra("price", price); i.putExtra("description", description); i.putExtra("color", tileColor);
            startActivity(i);
        });

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2); lp.bottomMargin = dp(14);
        root.addView(card, lp);
    }
}
