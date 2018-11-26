import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITblTermine } from 'app/shared/model/tbl-termine.model';
import { TblTermineService } from './tbl-termine.service';

@Component({
    selector: 'jhi-tbl-termine-delete-dialog',
    templateUrl: './tbl-termine-delete-dialog.component.html'
})
export class TblTermineDeleteDialogComponent {
    tblTermine: ITblTermine;

    constructor(private tblTermineService: TblTermineService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tblTermineService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tblTermineListModification',
                content: 'Deleted an tblTermine'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tbl-termine-delete-popup',
    template: ''
})
export class TblTermineDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tblTermine }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TblTermineDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tblTermine = tblTermine;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
