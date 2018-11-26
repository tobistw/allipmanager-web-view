import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AllipmanagerwebviewSharedModule } from 'app/shared';
import {
    TblVertragComponent,
    TblVertragDetailComponent,
    TblVertragUpdateComponent,
    TblVertragDeletePopupComponent,
    TblVertragDeleteDialogComponent,
    tblVertragRoute,
    tblVertragPopupRoute
} from './';

const ENTITY_STATES = [...tblVertragRoute, ...tblVertragPopupRoute];

@NgModule({
    imports: [AllipmanagerwebviewSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TblVertragComponent,
        TblVertragDetailComponent,
        TblVertragUpdateComponent,
        TblVertragDeleteDialogComponent,
        TblVertragDeletePopupComponent
    ],
    entryComponents: [TblVertragComponent, TblVertragUpdateComponent, TblVertragDeleteDialogComponent, TblVertragDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AllipmanagerwebviewTblVertragModule {}
