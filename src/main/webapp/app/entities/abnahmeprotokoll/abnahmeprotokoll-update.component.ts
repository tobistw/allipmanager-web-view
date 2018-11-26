import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IAbnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';
import { AbnahmeprotokollService } from './abnahmeprotokoll.service';
import { IUser, UserService } from 'app/core';
import { ITblTermine } from 'app/shared/model/tbl-termine.model';
import { TblTermineService } from 'app/entities/tbl-termine';

@Component({
    selector: 'jhi-abnahmeprotokoll-update',
    templateUrl: './abnahmeprotokoll-update.component.html'
})
export class AbnahmeprotokollUpdateComponent implements OnInit {
    abnahmeprotokoll: IAbnahmeprotokoll;
    isSaving: boolean;

    users: IUser[];

    tbltermines: ITblTermine[];
    datumLeistungDp: any;
    datumDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private abnahmeprotokollService: AbnahmeprotokollService,
        private userService: UserService,
        private tblTermineService: TblTermineService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ abnahmeprotokoll }) => {
            this.abnahmeprotokoll = abnahmeprotokoll;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tblTermineService.query().subscribe(
            (res: HttpResponse<ITblTermine[]>) => {
                this.tbltermines = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.abnahmeprotokoll.id !== undefined) {
            this.subscribeToSaveResponse(this.abnahmeprotokollService.update(this.abnahmeprotokoll));
        } else {
            this.subscribeToSaveResponse(this.abnahmeprotokollService.create(this.abnahmeprotokoll));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAbnahmeprotokoll>>) {
        result.subscribe((res: HttpResponse<IAbnahmeprotokoll>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTblTermineById(index: number, item: ITblTermine) {
        return item.id;
    }
}
