import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITblAsp } from 'app/shared/model/tbl-asp.model';
import { TblAspService } from './tbl-asp.service';

@Component({
    selector: 'jhi-tbl-asp-delete-dialog',
    templateUrl: './tbl-asp-delete-dialog.component.html'
})
export class TblAspDeleteDialogComponent {
    tblAsp: ITblAsp;

    constructor(private tblAspService: TblAspService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tblAspService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tblAspListModification',
                content: 'Deleted an tblAsp'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tbl-asp-delete-popup',
    template: ''
})
export class TblAspDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tblAsp }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TblAspDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tblAsp = tblAsp;
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
