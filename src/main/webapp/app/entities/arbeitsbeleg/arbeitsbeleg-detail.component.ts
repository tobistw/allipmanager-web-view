import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IArbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';

@Component({
    selector: 'jhi-arbeitsbeleg-detail',
    templateUrl: './arbeitsbeleg-detail.component.html'
})
export class ArbeitsbelegDetailComponent implements OnInit {
    arbeitsbeleg: IArbeitsbeleg;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ arbeitsbeleg }) => {
            this.arbeitsbeleg = arbeitsbeleg;
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
