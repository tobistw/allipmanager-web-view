import { NgModule } from '@angular/core';

import { AllipmanagerwebviewSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [AllipmanagerwebviewSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [AllipmanagerwebviewSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AllipmanagerwebviewSharedCommonModule {}
