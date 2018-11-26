import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';

@Component({
    selector: 'jhi-tbl-funkmessung-detail',
    templateUrl: './tbl-funkmessung-detail.component.html'
})
export class TblFunkmessungDetailComponent implements OnInit {
    tblFunkmessung: ITblFunkmessung;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tblFunkmessung }) => {
            this.tblFunkmessung = tblFunkmessung;
        });
    }

    previousState() {
        window.history.back();
    }
}
