import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITblVertrag } from 'app/shared/model/tbl-vertrag.model';
import { TblVertragService } from './tbl-vertrag.service';

@Component({
    selector: 'jhi-tbl-vertrag-delete-dialog',
    templateUrl: './tbl-vertrag-delete-dialog.component.html'
})
export class TblVertragDeleteDialogComponent {
    tblVertrag: ITblVertrag;

    constructor(private tblVertragService: TblVertragService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tblVertragService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tblVertragListModification',
                content: 'Deleted an tblVertrag'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tbl-vertrag-delete-popup',
    template: ''
})
export class TblVertragDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tblVertrag }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TblVertragDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tblVertrag = tblVertrag;
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
