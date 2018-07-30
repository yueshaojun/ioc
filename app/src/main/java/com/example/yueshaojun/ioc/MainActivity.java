package com.example.yueshaojun.ioc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lib.Inject;
import com.example.yueshaojun.ioc.bean.Computer;
import com.example.yueshaojun.ioc.bean.Host;
import com.example.yueshaojun.ioc.container.Container;

/**
 * @author yueshaojun
 */
public class MainActivity extends AppCompatActivity {
    @Inject()
    Host host;

    Computer computer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        computer = (Computer) Container.get(Computer.class);

        Injector.bindMember(this);
        findViewById(R.id.tv_ioc_reflect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computer.getHost().setName("反射拿到了host！");
                Toast.makeText(MainActivity.this,computer.getHost().getName(),Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.tv_ioc_compiler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                host.setName("编译拿到了host！");
                Toast.makeText(MainActivity.this,host.getName(),Toast.LENGTH_SHORT).show();
            }
        });




    }
}
