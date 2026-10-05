package com.example.lojavisual;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class PaymentActivity extends Activity {
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);} private TextView text(String s,float z,int c,boolean b){TextView v=new TextView(this);v.setText(s);v.setTextSize(z);v.setTextColor(c);if(b)v.setTypeface(null,android.graphics.Typeface.BOLD);return v;} private GradientDrawable bg(int c,float r){GradientDrawable d=new GradientDrawable();d.setColor(c);d.setCornerRadius(dp(r));return d;}
    private EditText input(String hint){EditText e=new EditText(this);e.setHint(hint);e.setTextSize(15);e.setSingleLine(true);e.setPadding(dp(16),0,dp(16),0);e.setBackground(bg(Color.rgb(246,246,248),14));return e;}

    @Override public void onCreate(Bundle b){
        super.onCreate(b); getWindow().setStatusBarColor(Color.rgb(255,249,244)); getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        String emoji=getIntent().getStringExtra("emoji"), name=getIntent().getStringExtra("name"), price=getIntent().getStringExtra("price"); int qty=getIntent().getIntExtra("qty",1), color=getIntent().getIntExtra("color",Color.LTGRAY);
        ScrollView scroll=new ScrollView(this); scroll.setBackgroundColor(Color.rgb(255,249,244)); LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(20),dp(16),dp(20),dp(30)); scroll.addView(root);
        TextView back=text("‹  Voltar",16,Color.rgb(70,70,76),true); back.setOnClickListener(v->finish()); root.addView(back);
        TextView title=text("Pagamento",28,Color.rgb(30,30,36),true); LinearLayout.LayoutParams tlp=new LinearLayout.LayoutParams(-1,-2);tlp.topMargin=dp(18);root.addView(title,tlp);
        TextView sub=text("Finalize sua compra com segurança",14,Color.rgb(110,110,116),false);root.addView(sub);

        LinearLayout summary=new LinearLayout(this);summary.setOrientation(LinearLayout.HORIZONTAL);summary.setGravity(Gravity.CENTER_VERTICAL);summary.setPadding(dp(12),dp(12),dp(12),dp(12));summary.setBackground(bg(Color.WHITE,20));LinearLayout.LayoutParams smp=new LinearLayout.LayoutParams(-1,-2);smp.topMargin=dp(22);root.addView(summary,smp);
        TextView icon=text(emoji,34,Color.BLACK,false);icon.setGravity(Gravity.CENTER);icon.setBackground(bg(color,14));summary.addView(icon,new LinearLayout.LayoutParams(dp(72),dp(72)));
        LinearLayout inf=new LinearLayout(this);inf.setOrientation(LinearLayout.VERTICAL);inf.setPadding(dp(14),0,0,0);summary.addView(inf,new LinearLayout.LayoutParams(0,-2,1));inf.addView(text(name,16,Color.rgb(35,35,40),true));inf.addView(text("Quantidade: "+qty,13,Color.rgb(110,110,116),false));TextView total=text(price,18,Color.rgb(255,107,53),true);summary.addView(total);

        TextView method=text("Forma de pagamento",17,Color.rgb(35,35,40),true);LinearLayout.LayoutParams mlp=new LinearLayout.LayoutParams(-1,-2);mlp.topMargin=dp(26);root.addView(method,mlp);
        RadioGroup rg=new RadioGroup(this);rg.setOrientation(LinearLayout.HORIZONTAL);RadioButton card=new RadioButton(this);card.setText("💳 Cartão");card.setChecked(true);RadioButton pix=new RadioButton(this);pix.setText("◆ PIX");rg.addView(card,new RadioGroup.LayoutParams(0,dp(52),1));rg.addView(pix,new RadioGroup.LayoutParams(0,dp(52),1));root.addView(rg);

        LinearLayout fields=new LinearLayout(this);fields.setOrientation(LinearLayout.VERTICAL);root.addView(fields);
        EditText holder=input("Nome no cartão");EditText number=input("Número do cartão");number.setInputType(InputType.TYPE_CLASS_NUMBER);EditText expiry=input("Validade (MM/AA)");EditText cvv=input("CVV");cvv.setInputType(InputType.TYPE_CLASS_NUMBER);
        addField(fields,holder);addField(fields,number);LinearLayout row=new LinearLayout(this);row.setOrientation(LinearLayout.HORIZONTAL);LinearLayout.LayoutParams elp=new LinearLayout.LayoutParams(0,dp(56),1);elp.rightMargin=dp(8);row.addView(expiry,elp);LinearLayout.LayoutParams clp=new LinearLayout.LayoutParams(0,dp(56),1);clp.leftMargin=dp(8);row.addView(cvv,clp);LinearLayout.LayoutParams rlp=new LinearLayout.LayoutParams(-1,dp(56));rlp.topMargin=dp(10);fields.addView(row,rlp);
        TextView pixBox=text("Chave PIX gerada no momento da compra.\nPagamento instantâneo e seguro.",15,Color.rgb(70,70,78),false);pixBox.setPadding(dp(16),dp(18),dp(16),dp(18));pixBox.setBackground(bg(Color.WHITE,16));pixBox.setVisibility(View.GONE);LinearLayout.LayoutParams pxlp=new LinearLayout.LayoutParams(-1,-2);pxlp.topMargin=dp(12);root.addView(pixBox,pxlp);
        rg.setOnCheckedChangeListener((group,id)->{boolean isPix=id==pix.getId();fields.setVisibility(isPix?View.GONE:View.VISIBLE);pixBox.setVisibility(isPix?View.VISIBLE:View.GONE);});

        Button finish=new Button(this);finish.setText("Finalizar pagamento");finish.setTextColor(Color.WHITE);finish.setTextSize(15);finish.setTypeface(null,android.graphics.Typeface.BOLD);finish.setAllCaps(false);finish.setBackground(bg(Color.rgb(255,107,53),18));LinearLayout.LayoutParams flp=new LinearLayout.LayoutParams(-1,dp(60));flp.topMargin=dp(26);root.addView(finish,flp);
        finish.setOnClickListener(v->new AlertDialog.Builder(this).setTitle("✅ Pagamento aprovado!").setMessage("Sua compra foi concluída com sucesso.\n\nEste pagamento é apenas uma simulação visual para o trabalho.").setPositiveButton("Voltar ao início",(d,w)->{android.content.Intent i=new android.content.Intent(this,MainActivity.class);i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP|android.content.Intent.FLAG_ACTIVITY_NEW_TASK);startActivity(i);finish();}).setNegativeButton("Fechar",null).show());
        TextView note=text("Ambiente demonstrativo • nenhum dado é enviado",12,Color.rgb(130,130,136),false);note.setGravity(Gravity.CENTER);LinearLayout.LayoutParams nlp=new LinearLayout.LayoutParams(-1,-2);nlp.topMargin=dp(12);root.addView(note,nlp);
        setContentView(scroll);
    }
    private void addField(LinearLayout p,EditText e){LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,dp(56));lp.topMargin=dp(10);p.addView(e,lp);}
}
