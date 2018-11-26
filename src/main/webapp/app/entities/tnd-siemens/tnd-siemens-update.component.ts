import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { ITndSiemens } from 'app/shared/model/tnd-siemens.model';
import { TndSiemensService } from './tnd-siemens.service';

@Component({
    selector: 'jhi-tnd-siemens-update',
    templateUrl: './tnd-siemens-update.component.html'
})
export class TndSiemensUpdateComponent implements OnInit {
    tndSiemens: ITndSiemens;
    isSaving: boolean;
    tndWartenDp: any;
    tndDslFristDp: any;

    constructor(private tndSiemensService: TndSiemensService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tndSiemens }) => {
            this.tndSiemens = tndSiemens;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tndSiemens.id !== undefined) {
            this.subscribeToSaveResponse(this.tndSiemensService.update(this.tndSiemens));
        } else {
            this.subscribeToSaveResponse(this.tndSiemensService.create(this.tndSiemens));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITndSiemens>>) {
        result.subscribe((res: HttpResponse<ITndSiemens>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
