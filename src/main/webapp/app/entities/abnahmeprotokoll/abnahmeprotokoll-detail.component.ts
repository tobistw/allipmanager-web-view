import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAbnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';

@Component({
    selector: 'jhi-abnahmeprotokoll-detail',
    templateUrl: './abnahmeprotokoll-detail.component.html'
})
export class AbnahmeprotokollDetailComponent implements OnInit {
    abnahmeprotokoll: IAbnahmeprotokoll;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ abnahmeprotokoll }) => {
            this.abnahmeprotokoll = abnahmeprotokoll;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
