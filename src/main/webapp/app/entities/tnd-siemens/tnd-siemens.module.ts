import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AllipmanagerwebviewSharedModule } from 'app/shared';
import {
    TndSiemensComponent,
    TndSiemensDetailComponent,
    TndSiemensUpdateComponent,
    TndSiemensDeletePopupComponent,
    TndSiemensDeleteDialogComponent,
    tndSiemensRoute,
    tndSiemensPopupRoute
} from './';

const ENTITY_STATES = [...tndSiemensRoute, ...tndSiemensPopupRoute];

@NgModule({
    imports: [AllipmanagerwebviewSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TndSiemensComponent,
        TndSiemensDetailComponent,
        TndSiemensUpdateComponent,
        TndSiemensDeleteDialogComponent,
        TndSiemensDeletePopupComponent
    ],
    entryComponents: [TndSiemensComponent, TndSiemensUpdateComponent, TndSiemensDeleteDialogComponent, TndSiemensDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AllipmanagerwebviewTndSiemensModule {}
