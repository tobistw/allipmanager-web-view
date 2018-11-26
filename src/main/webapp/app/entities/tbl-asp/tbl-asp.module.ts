import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AllipmanagerwebviewSharedModule } from 'app/shared';
import {
    TblAspComponent,
    TblAspDetailComponent,
    TblAspUpdateComponent,
    TblAspDeletePopupComponent,
    TblAspDeleteDialogComponent,
    tblAspRoute,
    tblAspPopupRoute
} from './';

const ENTITY_STATES = [...tblAspRoute, ...tblAspPopupRoute];

@NgModule({
    imports: [AllipmanagerwebviewSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TblAspComponent, TblAspDetailComponent, TblAspUpdateComponent, TblAspDeleteDialogComponent, TblAspDeletePopupComponent],
    entryComponents: [TblAspComponent, TblAspUpdateComponent, TblAspDeleteDialogComponent, TblAspDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AllipmanagerwebviewTblAspModule {}
