import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AllipmanagerwebviewTblTermineModule } from './tbl-termine/tbl-termine.module';
import { AllipmanagerwebviewTblFunkmessungModule } from './tbl-funkmessung/tbl-funkmessung.module';
import { AllipmanagerwebviewDashboardModule } from './dashboard/dashboard.module';
import { AllipmanagerwebviewArbeitsbelegModule } from './arbeitsbeleg/arbeitsbeleg.module';
import { AllipmanagerwebviewAbnahmeprotokollModule } from './abnahmeprotokoll/abnahmeprotokoll.module';
import { AllipmanagerwebviewTblVertragModule } from './tbl-vertrag/tbl-vertrag.module';
import { AllipmanagerwebviewTndSiemensModule } from './tnd-siemens/tnd-siemens.module';
import { AllipmanagerwebviewTblAspModule } from './tbl-asp/tbl-asp.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        AllipmanagerwebviewTblTermineModule,
        AllipmanagerwebviewTblFunkmessungModule,
        AllipmanagerwebviewDashboardModule,
        AllipmanagerwebviewArbeitsbelegModule,
        AllipmanagerwebviewAbnahmeprotokollModule,
        AllipmanagerwebviewTblVertragModule,
        AllipmanagerwebviewTndSiemensModule,
        AllipmanagerwebviewTblAspModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AllipmanagerwebviewEntityModule {}
