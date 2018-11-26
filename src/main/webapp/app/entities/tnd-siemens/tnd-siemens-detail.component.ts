import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITndSiemens } from 'app/shared/model/tnd-siemens.model';

@Component({
    selector: 'jhi-tnd-siemens-detail',
    templateUrl: './tnd-siemens-detail.component.html'
})
export class TndSiemensDetailComponent implements OnInit {
    tndSiemens: ITndSiemens;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tndSiemens }) => {
            this.tndSiemens = tndSiemens;
        });
    }

    previousState() {
        window.history.back();
    }
}
