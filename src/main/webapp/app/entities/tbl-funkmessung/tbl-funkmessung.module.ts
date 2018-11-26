import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AllipmanagerwebviewSharedModule } from 'app/shared';
import {
    TblFunkmessungComponent,
    TblFunkmessungDetailComponent,
    TblFunkmessungUpdateComponent,
    TblFunkmessungDeletePopupComponent,
    TblFunkmessungDeleteDialogComponent,
    tblFunkmessungRoute,
    tblFunkmessungPopupRoute
} from './';

const ENTITY_STATES = [...tblFunkmessungRoute, ...tblFunkmessungPopupRoute];

@NgModule({
    imports: [AllipmanagerwebviewSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TblFunkmessungComponent,
        TblFunkmessungDetailComponent,
        TblFunkmessungUpdateComponent,
        TblFunkmessungDeleteDialogComponent,
        TblFunkmessungDeletePopupComponent
    ],
    entryComponents: [
        TblFunkmessungComponent,
        TblFunkmessungUpdateComponent,
        TblFunkmessungDeleteDialogComponent,
        TblFunkmessungDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AllipmanagerwebviewTblFunkmessungModule {}
