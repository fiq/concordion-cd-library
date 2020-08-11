package com.example.concordion.cdlibrary.di;

import com.example.concordion.cdlibrary.BuyCdFixture;
import com.example.concordion.cdlibrary.service.PurchasingServiceImplTest;
import dagger.Component;

@Component(modules = NameModule.class)
public interface TestComponent {
    void inject(BuyCdFixture buyCdFixture);
    void inject(PurchasingServiceImplTest purchasingServiceImplTest);

}
