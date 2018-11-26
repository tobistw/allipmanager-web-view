import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AllipmanagerwebviewSharedModule } from 'app/shared';
import { AllipmanagerwebviewAdminModule } from 'app/admin/admin.module';
import {
    ArbeitsbelegComponent,
    ArbeitsbelegDetailComponent,
    ArbeitsbelegUpdateComponent,
    ArbeitsbelegDeletePopupComponent,
    ArbeitsbelegDeleteDialogComponent,
    arbeitsbelegRoute,
    arbeitsbelegPopupRoute
} from './';

const ENTITY_STATES = [...arbeitsbelegRoute, ...arbeitsbelegPopupRoute];

@NgModule({
    imports: [AllipmanagerwebviewSharedModule, AllipmanagerwebviewAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ArbeitsbelegComponent,
        ArbeitsbelegDetailComponent,
        ArbeitsbelegUpdateComponent,
        ArbeitsbelegDeleteDialogComponent,
        ArbeitsbelegDeletePopupComponent
    ],
    entryComponents: [
        ArbeitsbelegComponent,
        ArbeitsbelegUpdateComponent,
        ArbeitsbelegDeleteDialogComponent,
        ArbeitsbelegDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AllipmanagerwebviewArbeitsbelegModule {}
