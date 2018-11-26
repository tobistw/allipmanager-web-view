import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { ITblTermine } from 'app/shared/model/tbl-termine.model';
import { TblTermineService } from './tbl-termine.service';
import { IUser, UserService } from 'app/core';
import { ITblVertrag } from 'app/shared/model/tbl-vertrag.model';
import { TblVertragService } from 'app/entities/tbl-vertrag';
import { ITndSiemens } from 'app/shared/model/tnd-siemens.model';
import { TndSiemensService } from 'app/entities/tnd-siemens';

@Component({
    selector: 'jhi-tbl-termine-update',
    templateUrl: './tbl-termine-update.component.html'
})
export class TblTermineUpdateComponent implements OnInit {
    tblTermine: ITblTermine;
    isSaving: boolean;

    users: IUser[];

    tblvertrags: ITblVertrag[];

    tndsiemens: ITndSiemens[];
    terIbsDatumDp: any;
    terDatumNeuDp: any;
    terDslFristDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private tblTermineService: TblTermineService,
        private userService: UserService,
        private tblVertragService: TblVertragService,
        private tndSiemensService: TndSiemensService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tblTermine }) => {
            this.tblTermine = tblTermine;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tblVertragService.query().subscribe(
            (res: HttpResponse<ITblVertrag[]>) => {
                this.tblvertrags = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tndSiemensService.query().subscribe(
            (res: HttpResponse<ITndSiemens[]>) => {
                this.tndsiemens = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tblTermine.id !== undefined) {
            this.subscribeToSaveResponse(this.tblTermineService.update(this.tblTermine));
        } else {
            this.subscribeToSaveResponse(this.tblTermineService.create(this.tblTermine));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITblTermine>>) {
        result.subscribe((res: HttpResponse<ITblTermine>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackTblVertragById(index: number, item: ITblVertrag) {
        return item.id;
    }

    trackTndSiemensById(index: number, item: ITndSiemens) {
        return item.id;
    }
}
